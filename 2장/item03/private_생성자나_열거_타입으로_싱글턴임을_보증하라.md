> REFERENCE:
>

[이펙티브 자바 Effective Java 3/E - YES24](http://www.yes24.com/Product/Goods/65551284)

[study/effective-java at master · keesun/study](https://github.com/keesun/study/tree/master/effective-java)

[[이팩티브 자바] #1 생성자 대신 static 팩토리 메소드를 고려해 볼 것](https://www.youtube.com/watch?v=X7RXP6EI-5E&list=PLfI752FpVCS8e5ACdi5dpwLdlVkn0QgJJ)

---

## 싱글톤이란?

인스턴스 오직 하나만 생성할 수 있는 클래스.

자세한 내용은 아래 페이지를 읽어보세요 :)

[싱글톤(Singleton) 패턴](https://www.notion.so/Singleton-9f822e5c1f644aa98cc2415f2de3fde1)

## 싱글톤을 만드는 방식

### 1️⃣. public static final 필드 방식의 싱글톤

```java
public class JJong{
	public static final JJong INSTANCE = new JJong();
	private JJong() { ... }
}
```

private 생성자는 public static final 필드인 JJong.INSTANCE를 초기화할 때 단 한번만 호출합니다.

리플랙션을 사용해서 privcate 생성자를 호출하는 방법을 제외하면 (해당 방식을 막고자 생성자 안에서 카운팅하거나 flag를 이용해서 예외를 던지게 할 수도 있지만) 생성자는 오직 최초 한번만 호출되고 JJong은 싱글톤이 된다.

**장점:**

- 이런 API 사용이 static 팩토리 메소드를 사용하는 방법에 비해 더 명확하고 더 간단하다.

**단점:**

- 클라이언트에서 사용하지 않더라도 인스턴스가 항생 생성이 된다
    - 따라서, 메모리 낭비가 발생한다.

### 2️⃣. 정적 팩토리 방식의 싱글톤

```java
public class JJong{
	private static final JJong INSTANCE = new JJong();
	private JJong () { ... }
	public static JJong getInstance() { return INSTANCE; }
}
```

**장점:**

- API를 변경하지 않고도 싱글톤으로 쓸지 않고도 싱글톤이 아니게 변경할 수 있다.
- 원한다면 정적 팩토리를 제네릭 싱글톤 팩토리로 만들 수 있다.
- 정적 팩토리의 메소드 참조를 공급자로 사용할 수 있다.

**단점:**

- 여전히 사용하지 않더라도 인스턴스가 생성된다.

### 1️⃣ 과 2️⃣ 의 직렬화

둘 중 하나의 방식으로 만든 싱글턴 클래스를 직렬화 하려면 단순히 Serializable을 구현한다고 선언하는 것으로 부족

```java
//싱글턴임을 보장해주는 readResolve 메서드
private Object readResolve() {
	// '진짜' JJong 를 반환하고, 가짜 JJong 은 가비지 컬렉터에 맡김
	return INSTANCE;
}
```

### 3️⃣. 열거 타입 방식의 싱글톤

```java
public enum JJong {
	INSTANCE;
}
```

`싱글톤을 만드는 세가지 방법 중 가장 바람직한 방법`

직렬화/역직렬화 할 때 코딩으로 문제를 해결할 필요도 없고, 리플렉션으로 호출되는 문제도 고민할 필요없는 방법이 있다. 코드는 좀 불편하게 느껴지지만 싱글톤을 구현하는 최선의 방법이다. 하지만 이 방법은 Enum 말고 다른 상위 클래스를 상속해야 한다면 사용할 수 없다. (하지만 인터페스는 구현할 수 있다.)