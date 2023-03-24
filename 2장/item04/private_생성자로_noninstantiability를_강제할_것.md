> REFERENCE:
>

[이펙티브 자바 Effective Java 3/E - YES24](http://www.yes24.com/Product/Goods/65551284)

[study/effective-java at master · keesun/study](https://github.com/keesun/study/tree/master/effective-java)

[https://github.com/woowacourse-study/2022-effective-java](https://github.com/woowacourse-study/2022-effective-java)

[[이팩티브 자바] #1 생성자 대신 static 팩토리 메소드를 고려해 볼 것](https://www.youtube.com/watch?v=X7RXP6EI-5E&list=PLfI752FpVCS8e5ACdi5dpwLdlVkn0QgJJ)

---

static 메소드와 satic 필드를 모아둔 클래스를 만든 경우 해당 클래스를 abstact로 만들어도 인스턴스를 만드는 걸 막을 순 없다. 상속 받아서 인스턴스를 만들 수 있기 때문이다.

또한, 아무런 생성자를 만들지 않은 경우 컴파일러가 기본적으로 아무 인자가 없는 public 생성자를 만들어주기 때문에 그런 경우에도 인스턴스를 만들 수 있다.

따라서, 명시적으로 private 생성자를 추가해야한다.

```java
// Noninstantiable utility class
public class UtilityClass {
    // Suppress default constructor for noninstantiability
    private UtilityClass() {
        throw new AssertionError();
    }
}
```

- AssetionError는 꼭 필요하진 않지만, 그렇게 하면 의도치 않게 생성자를 호출한 경우에 에러를 발생시킬 수 있고, private 생성자기 때문에 상속도 막을 수 있다.

## 0️⃣. 인스턴스화 하면 안좋은거야?

인스턴스화가 가능한 클래스의 경우 인스턴스 메소드(instance method)와 인스턴스 변수(instance variables)를 사용하여 객체를 조작할 수 있게 됩니다.

하지만, 인스턴스화가 금지된 클래스는 일반적으로 유틸리티 클래스나 상수를 제공하는 클래스 등의 역할을 하기 때문에, 인스턴스화가 되면 이러한 역할을 수행할 수 없게 됩니다. 따라서, 인스턴스화가 금지된 클래스를 인스턴스화하는 것은 **불필요한 객체 생성으로 인한 메모리 낭비와 성능 저하를 초래할 수 있습**니다.

그럼에도 불구하고, 기본적으로 정적 메소드, 정적 필드만 담은 클래스는 때때로 유용하게 쓰일 수 있다. 사용하는 경우가 크게 세 가지가 있는데, 하나씩 알아보자.

## 1️⃣. 기본 타입 값이나 배열 관련 메소드를 모은 클래스

ex)

`java.util.Arrays`의 코드를 살펴보면 아래와 같다.

```java
public class Arrays {
    public static boolean isArray(Object o) { ...}

    public static Object[] asObjectArray(Object array) { ...}

    public static List<Object> asList(Object array) { ...}

    public static <T> boolean isNullOrEmpty(T[] array) { ...}

    // ...

    private Arrays() {
    }
}
```

우리가 Array를 사용할 때를 생각해보면 절대 Array arrays = new Arrays()로 생성하지 않는다. Arrays.asList(배열) 식으로 바로 사용한다.

**내부 메소드를 보니 전부 static으로 선언되어 있고 생성자는 private으로 선언된 것을 확인할 수 있다.**

## 2️⃣. 특정 인터페이스를 구현한느 객체를 생성해주는 정적 메소드(or 팩토리)를 모은 클래스

ex) `java.util.Collections`

`java.util.Arrays`와 마찬가지로 static으로만 구성, private 생성자 존재하는 것을 확인할 수 있다.****

```java
public class Collections {
    // Suppresses default constructor, ensuring non-instantiability.
    private Collections() {
    }
		....
}
```

## 3️⃣. final 클래스와 관련된 메소드들을 모아놓을 때

final 클래스는 보안 상의 이유로 상속을 금지 시킵니다. 대표적으로 String 클래스가 있다.

```java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence { ... }
```

이러한 final 클래스와 관련된 메소드들을 모아둔 클래스가 존재한다면?

예제용으로 junit에 존재하는 StringUtils를 살펴보자.

```java
@API(status = INTERNAL, since = "1.0")
public final class StringUtils {
    private static final Pattern ISO_CONTROL_PATTERN = compileIsoControlPattern();
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\s");

    static Pattern compileIsoControlPattern() { ... }

    private StringUtils() {
        /* no-op */
    }

    public static boolean isBlank(String str) { ... }

    public static boolean isNotBlank(String str) { ... }

    public static boolean containsWhitespace(String str) { ... }

    // ...
}
```

마찬가지로 static으로 선언되어 바로 호출할 수 있다.****

## 4️⃣. ETC

정적 메소드를 담은 클래스들은 보통 **유틸리티 클래스**로 사용하기 위해 생성된다. 유틸리티 클래스는 기본적으로 **인스턴스로 만들어 쓰려고 설계한 것이 아니다**.

크게 세 가지로 구분해두었지만 모든 메소드가 static으로 이루어진 유틸리티 클래스에서 사용하면 된다.

### ❓ 생성자를 명시하지 않으면 되지 않을까?

- 생성자를 명시하지 않으면 **컴파일러가 자동으로 public으로 기본 생성자가 생성**해준다.사용자가 코드만 보고 생성자가 없다고 생각하더라도 컴파일 시 자동으로 생성된다.

### ❓ 추상 클래스로 만들어서 인스턴스화를 막기?

abstract 클래스는 인스턴스로 생성하는 것이 불가능하다.private 생성자 대신에 쓸 수 있지 않을까? 라는 생각이 들 수 있다.

- 하위 클래스를 생성하면 인스턴스화가 가능해진다.
- abstract 클래스는 보통 클래스들의 공통 필드와 메소드를 정의하는 목적으로 만들기 때문에 상속해서 사용하라는 의미로 오해할 수 있다.