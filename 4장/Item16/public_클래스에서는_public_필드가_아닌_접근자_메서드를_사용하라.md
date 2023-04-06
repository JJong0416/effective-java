> REFERENCE:
>

[이펙티브 자바 Effective Java 3/E - YES24](http://www.yes24.com/Product/Goods/65551284)

[https://github.com/woowacourse-study/2022-effective-java](https://github.com/woowacourse-study/2022-effective-java)

[item 16. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라 In public classes, use accessor methods, not public fields](https://sihyung92.oopy.io/java/effective-java/16)

[아이템 16. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라](https://velog.io/@banjjoknim/아이템-16.-public-클래스에서는-public-필드가-아닌-접근자-메서드를-사용하라)

---

## 1️⃣. 캡슐화의 이점을 제공하지 못하는 클래스

```java
class Point {
	public int x;
	public int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
```

### A) API를 수정하지 않고는 내부 표현을 바꿀 수 없습니다.

- getter, setter를 이용하면 표현 변경 가능해진다.

### B) 불변식을 보장할 수 없다.

- 클라이언트가 직접 값을 변경할 수 있다.

### C) 외부에서 필드에 접근할 때 부수 작업을 수행할 수 없다.

- 1차원 적인 접근만 가능하고, 추가 로직을 삽입할 수 없게 됩니다.

## 2️⃣. 철저한 객체 지향 프로그래머가 캡슐화한 클래스

```java
public class Point {
	private int x;
	private int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() { return x; }
	public int getY() { return y; }

	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
}
```

1. 필드들을 모두 private으로 변경
2. public 접근자(getter), 변경자(setter)를 추가합니다.

### 유연성을 얻을 수 있다.

패키지 바깥에서 접근할 수 있는 클래스라면 접근자를 제공함으로써 클래스 내부 표현 방식을 언제든 바꿀 수 있는 **유연성을 얻을 수 있습니다**

## 3️⃣. private 중첩 클래스

```java
public class TopPoint {
	private static class Point {
		public double x;
		public double y;
	}

	public Point getPoint() {
		Point point = new Point();
		point.x = 3.5;
		point.y = 4.5;
		return point;
	}
}
```

### 처음 제시된 3가지 문제점을 해결할 수 있다.

- TopPoint 클래스에서는 얼마든지 Point  클래스의 필드를 조작할 수 있다.
- 외부 클래스에서는 Point 클래스의 필드에 직접 접근할 수 없다.

### **package-private 클래스 역시 3가지 문제 해결할 수 있음**

- 해당 클래스가 포함되는 패키지 내에서만 조작할 수 있음
- 패키지 외부에서는 접근할 수 없음

## 4️⃣. 불변 필드를 노출한 public 클래스는 과연 좋을까?

```java
public class Time {
    private static final int HOURS_PER_DAY = 24;
    private static final int MINUTES_PER_HOUR = 60;

    public final int hour;
    public final int minute;

    public Time(int hour, int minute) {
        if (hour < 0 || hour > HOURS_PER_DAY) {
            throw new IllegalArgumentException("시간: " + hour);
        }
        if (minute < 0 || minute > MINUTES_PER_HOUR) {
            throw new IllegalArgumentException("분: " + minute);
        }
        this.hour = hour;
        this.minute = minute;
    }
	...
}
```

public 클래스의 필드가 불변이라면 직접 노출할 때의 단점이 조금은 줄어들지만, 여전히 결코 좋은 생각은 아니다. **API를 변경하지 않고는 표현 방식을 바꿀 수 없고, 필드를 읽을 때때 부수 작업을 수행할 수 없다는 단점**이 여전하다.

단, 불변식은 보장할 수 있게 됩니다. 예컨데 다음 클래스는 각 인스턴스가 유요한 시간을 표현함은 보장되어 있다.

## cf) getter, setter는 옳은걸까

친구들과 무분별한 getter, setter는 사실상 캡슐화를 깨는 것과 동일하지 않는가 하는 이야기를 자주 나누곤 했다. get/set을 지양하고 의미있는 메서드를 만들어 호출하는 것이 객체지향적인 코드를 작성하기 위한 원칙으로 삼았었다.
그러나 public 필드보다 많은 이점을 가지고 있다는 걸 이 책을 통해 느꼈다.