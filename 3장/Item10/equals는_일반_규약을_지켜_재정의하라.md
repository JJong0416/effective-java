> REFERENCE:
>

[이펙티브 자바 Effective Java 3/E - YES24](http://www.yes24.com/Product/Goods/65551284)

[https://github.com/woowacourse-study/2022-effective-java](https://github.com/woowacourse-study/2022-effective-java)

[Item 10. Equals는 일반 규약을 지켜 재정의하라 | Carrey`s 기술블로그](https://jaehun2841.github.io/2019/01/10/effective-java-item10/#추이성이-위반되는-case)

---

## 1️⃣. equals 메소드

equals 메소드는 Object 클래스에 구현된 메소드로 객체 내의 정보들에 대한 동등성(equality) 비교를 목적으로 하는 메소드입니다.

equals 메소드를 잘못 작성하게 되면 의도하지 않는 결과물들이 초래되므로 필요하지 않는 이상 재정의 하지 않는 것이 좋다, 그럼 어떤 케이스에 재정의 안해도 좋을까?

## 2️⃣. equals를 재정의 하지 않는 것이 최선인 경우

### 1) **각 인스턴스가 본질적으로 고유하다.**

- 각 인스턴스가 본질적으로 **고유**한 경우,  값이 아닌 동작을 표현하는 클래스의 경우(ex. Thread)가 여기에 해당이 됩니다.
- Thread에서 equals의 코드를 보면 아래와 같다.

```java
@Override
public boolean equals(Object obj) {
		if (obj == this)
				return true;

		if (obj instanceof WeakClassKey) {
        Object referent = get();
        return (referent != null) &&
               (referent == ((WeakClassKey) obj).get());
    } else {
        return false;
    }
}
```

### 2) 인스턴스의 ‘논리적 동치성’을 검사할 일이 없다.

- equals를 재정의하여 각 인스턴스가 같은 정규표현식을 나타내는지 검사할 순 있지만, 그럴 일이 없다면 굳이 재정의할 필요가 없다. (ex: Pattern)

```java
public static void main(String[] args) {
        final Pattern P1 = Pattern.compile("//.*");
        final Pattern P2 = Pattern.compile("//.*");

        System.out.println(P1.equals(P1)); // true
        System.out.println(P1.equals(P2)); // false
        System.out.println(P1.pattern()); // //.*
        System.out.println(P1.pattern().equals(P1.pattern())); // true
        System.out.println(P1.pattern().equals(P2.pattern())); // true
}
```

### 3) 상위 클래스에서 재정의한 equals가 하위 클래스에도 딱 들어맞는다.

- Set의 구현체들은 모두 AbstractSet이 구현한 equals를 상속 받아서 쓴다. 그 이유는 서로 같은 Set인지 구분하기 위해서 사이즈, 내부 값들을 비교하면 되기 때문이다.

```java
public boolean equals(Object o) {
    if (o == this) {
        return true;
    }

    if (!(o instanceof Set)) {
        return false;
    }
    
    Collection<?> c = (Collection<?>) o;
    if (c.size() != size()) { // 사이즈 비교
        return false;
    }
    try {
        return containsAll(c); // 내부 인스턴스 비교
    } catch (ClassCastException | NullPointerException unused) {
        return false;
    }
}
```

- 그런데, 그로 인해 이런 일도 생길 수 있다.

```java
void setTest() {
    Set<String> hash = new HashSet<>();
    Set<String> tree = new TreeSet<>();
    hash.add("Set");
    tree.add("Set");

    System.out.println(hash.equals(tree)) // true;
}
```

### 4) 클래스가 private이거나 package-private이고 equals 메소드를 호출할 일이 없다.

```java
@Override 
public boolean equals(Object o){
    throw new AssertionError(); // 호출 금지
}
```

## 3️⃣. 그러면 언제 equals를 재정의 할까?

**equlas는 논리적인 동치성을 확인하고 싶을 때 재정의를 한다.**

## 4️⃣. equals를 재정의 하는 경우 지켜야 할 규약

재정의하는 경우에 지켜야 할 규약이 하나 있다. 바로 equals를 재정의 해야 하는 경우는 객체 동일성(식별성(Object Identity))를 확인해야 하는 경우가 아니라 논리적 동치성(동등성(Logical equality))를 비교하도록 재정의 되지 않았을 경우이다.

## 4️⃣. equals의 일반 규약

### 1) 반사성

- **null이 아닌 모든 참조 x값에 대해 x.equals(x)를 만족해야 한다.**
    - x.equals(x) == true
- 자기 자신과 같아야 한다.
- 해당 조건을 만족하지 않느 예시를 찾기가 더 어려울 정도로, 모든 규약을 가지고 있다.

### 2) 대칭성:

- null이 아닌 모든 참조값 x, y에 대해 x.equals(y)가 true이면, y.equals(x)가 true를 만족해야한다.
- 서로에 대한 동치 여부가 같아야 한다.

```java
public class CaseInsensitiveString {

    private final String str;

    public CaseInsensitiveString(final String str) {
        this.str = str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CaseInsensitiveString){
            return str.equalsIgnoreCase(((CaseInsensitiveString) obj).str);
        }

        if (obj instanceof String) {
            return str.equalsIgnoreCase((String) obj);
        }
        return false;
    }
}
```

- `equalsIgnoreCase`는 대소문자를 구분하지 않고 비교하는 함수이다.
- 위 클래스를 기반으로

```java
public class StringTest {
    public static void main(String[] args) {
        CaseInsensitiveString caseString = new CaseInsensitiveString("Test");
        String test = "test";

        System.out.println(caseString.equals(test));
        System.out.println(test.equals(caseString));
    }
}
```

- 위의 코드를 실행하게 되면, x.equals(y)가 true일 때, y.equals(x)가 false이므로 대칭성이 깨지는 코드가 된다.
- String 클래스에서는 CaseInsensitiveString의 존재를 모르기 때문에 false가 날 수 밖에 없는 상황인 것이다.

### 3) 추이성

- **null이 아닌 모든 참조 값 x, y, z에 대해 x.equals(y) = true, y.equals(z) = true이면 x.equals(z)도 true가 되어야 한다는 조건이다.**

```java
public class Point {

    private final int x;
    private final int y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }
        Point p = (Point) o;
        return this.x == p.x && this.y == p.y;
    }
}
```

```java
public class ColorPoint extends Point {

    private final Color color;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }

        // o가 일반 Point이면 색상을 무시햐고 x,y 정보만 비교한다.
        if (!(o instanceof ColorPoint)) {
            return o.equals(this);
        }

        // o가 ColorPoint이면 색상까지 비교한다.
        return super.equals(o) && this.color == ((ColorPoint) o).color;
    }
}
```

```java
void transitivityTest() {
    ColorPoint a = new ColorPoint(2, 3, Color.RED);
    Point b = new Point(2, 3);
    ColorPoint c = new ColorPoint(2, 3, Color.BLUE);

    System.out.println(a.equals(b)); // true
    System.out.println(b.equals(c)); // true
    System.out.println(a.equals(c)); // false
}
```

1. `a.equals(b)`를 보면 a는 ColorPoint이기 떄문에 ColorPoint 클래스에서 재정의 된 equals 메서드를 타게 된다.이렇게 되면 첫번째 if 조건에서 걸리게 된다. b는 Point이지만 ColorPoint는 아니기 떄문이다.따라서 `a.equals(b)`는 `false`가 된다.
2. `b.equals(a)`를 보면 b는 Point클래스이기 떄문에 Point클래스의 equals메서드를 타게 된다.이렇게 되면 ColorPoint는 Point클래스를 상속하고 있기 때문에 첫번째 if조건을 통과하게 되고,int x, int y값을 기준으로만 비교하기 떄문에 값이 참이 된다.따라서 `b.equals(a)`는 `true`가 된다.

### 3-1) 추이성(무한 재귀)

```java
class SmellPoint extends Point {
  
  private final Smell smell;

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof Point)) return false;

    //o가 일반 Point이면 색상을 무시햐고 x,y정보만 비교한다.
    if(!(o instanceof SmellPoint)) return o.equals(this);
    
    //o가 ColorPoint이면 색상까지 비교한다.
    return super.equals(o) && this.smell == ((SmellPoint) o).smell;
  }
}
```

위 처럼 Point를 상속받는 SmellPoint라는 클래스도 추가되었다고 가정해보자

```java
Point cp = new ColorPoint(1, 2, Color.RED);
Point sp = new SmellPoint(1, 2, Smell.SWEET);

System.out.println(cp.equals(sp));  //?
```

위와 같이 ColorPoint와 SmellPoint에 대해 equals비교를 한다고 하면 어떻게 될까? 이렇게 되면 두번째 if절에서 무한재귀(Infinite Recursion)이 발생하게 된다.

이런 작업이 계속 재귀적으로 호출 되면서, 결국은 StackOverflowError를 내며 프로그램이 죽는 참사를 맞이 하게 될 것이다.

### 3-2) 추이성(리스코프 치환 원칙)

만약 추이성을 지키기 위해서 Point의 equals를 각 클래스들을 getClass를 통해서 같은 구체 클래스일 경우에만 비교하도록 하면 어떻게 될까요?

```java
@Override
public boolean equals(Object o) {
    // getClass
    if (o == null || o.getClass() != this.getClass()) {
        return false;
    }

    Point p = (Point) o;
    return this.x == p.x && this.y = p.y;
}
```

이렇게 되면, 동작은 하지만 리스코프 치환 원칙을 지키지 못하게 된다.

```java
리스코프 치환 원칙이란?
자식 클래스는 최소한 자신의 부모 클래스에서 가능한 행위는 수행할 수 있어야 한다.
```

이는 모든 객체 지향 언어의 동치관계에서 나타나는 근본적인 문제이며, 객체 지향적 추상화의 이점을 포기하지 않는 한 불가능하다.

### 3-3) 추이성(상속 대신 컴포지션(아이템 18)을 사용해라)

```java
public class ColorPoint2 {

    private Point point;
    private Color color;

    public ColorPoint2(int x, int y, Color color) {
        this.point = new Point(x, y);
        this.color = Objects.requireNonNull(color);
    }

    public Point asPoint() {
        return this.point;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ColorPoint)) {
            return false;
        }
        ColorPoint cp = (ColorPoint) o;
        return this.point.equals(cp) && this.color.equals(cp.color);
    }
```

이와 같이 컴포지션을 이용하면 상속에 때문에 발생하는 대칭성, 추이성, 리스코프 치환원칙에 위배되지 않는 코드를 작성할 수 있다

### 4) 일관성

- **null이 아닌 모든 참조 값 x, y에 대해, x.equals(y)를 반복해서 호출하면 항상 true를 반환하거나 항상 false를 반환한다.**

```java
@Test
void consistencyTest() throws MalformedURLException {
    URL url1 = new URL("www.xxx.com");
    URL url2 = new URL("www.xxx.com");

    System.out.println(url1.equals(url2)); // 항상 같지 않다.
}
```

java.net.URL 클래스는 URL과 매핑된 host의 IP주소를 이용해 비교하기 때문에 같은 도메인 주소라도 나오는 IP정보가 달라질 수 있기 때문에 반복적으로 호출할 경우 결과가 달라질 수 있다. 따라서 이런 문제를 피하려면 equals는 항시 메모리에 존재하는 객체만을 사용한 결정적 계산을 수행해야 한다.

### 5) null 아님

- **null이 아닌 모든 참조 값 x에 대해, x.equals(null)은 false**
- `o.equals(null) == true`인 경우는 상상하기 어렵지만, 실수로 `NullPointerException`을 던지는 코드 또한 허용하지 않는다.
- 이러한 `Exception` 을 막기 위해서 여러가지 방법이 존재하는데, 그중 하나는 null인지 확인을 하고 `false`를 반환하는 것이다. 하지만 책에서는 다른 방법을 추천하고 있다.

```java
@Override 
public boolean equals(Object o) {
    // 우리가 흔하게 인텔리제이를 통해서 생성하는 equals는 다음과 같다.
    if (o == null || getClass() != o.getClass()) {
        return false;
    }
    
    // 책에서 추천하는 내용은 null 검사를 할 필요 없이 instanceof를 이용하라는 것이다.
    // instanceof는 두번째 피연산자(Point)와 무관하게 첫번째 피연산자(o)거 null이면 false를 반환하기 때문이다. 
    if (!(o instanceof Point)) {
        return false;
    }
}
```

## 5️⃣. equals 좋은 재정의 방법

```java
@Override
public boolean equals(final Object o) {
    // 1. == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.
    if (this == o) {
        return true;
    }

    // 2. instanceof 연산자로 입력이 올바른 타입인지 확인한다.
    if (!(o instanceof Point)) {
        return false;
    }

    // 3. 입력을 올바른 타입으로 형변환 한다.
    final Piece piece = (Piece) o;

    // 4. 입력 개체와 자기 자신의 대응되는 핵심 필드들이 모두 일치하는지 하나씩 검사한다.
    
    // float와 double을 제외한 기본 타입 필드는 ==를 사용한다.
    return this.x == p.x && this.y == p.y;
    
    // 필드가 참조 차입이라면 equals를 사용한다.
    return str1.equals(str2);
    
    // null 값을 정상 값으로 취급한다면 Objects.equals로 NullPointException을 예방하자.
    return Objects.equals(Object, Object);
}
```

## 6️⃣. 마무리

- 꼭 필요한 경우가 아니라면 재정의를 사용하지 말자.
- 그래도 필요하다면 핵심필드를 빠짐 없이 비교하며 다섯 가지 규약을 지키자.
- 어떤 필드를 먼저 비교하느냐에 따라 equals 성능을 좌우하기도 한다. 최상의 성능을 바란다면 다를 가능성이 더 크거나 비교하는 비용이 더 싼 필드를 먼저 비교하자.
- equals를 재정의할 때 hashCode도 반드시 재정의하자.
- 핵심 필드로부터 파생되는 필드가 있다면 굳이 둘 다 비교할 필요가 없고, 편한쪽을 선택하자.
- equals의 매개변수 입력을 Object가 아닌 타입으로는 선언하지 말자.
    - @Override 애노테이션을 일관되게 사용하는 것이 중요하기 때문이다.