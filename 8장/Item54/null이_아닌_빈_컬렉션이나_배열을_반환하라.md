[이펙티브 자바 Effective Java 3/E - YES24](http://www.yes24.com/Product/Goods/65551284)

[https://github.com/woowacourse-study/2022-effective-java](https://github.com/woowacourse-study/2022-effective-java)

---

## 1️⃣. Null을 반환하지 말 것

컬렉션이나 배열같은 컨테이너(Container)가 비었으면 null을 반환하는 경우가 있을 것입니다.

```java
private final List<Car> cars;

public List<Car> getCars() {
    if (cars.isEmpty()) {
        return null;
    }
    return new ArrayList<>(cars);
}
```

하지만 null을 반환하게 된다면 메소드를 사용하는 곳에서는 반드시 null에 대한 처리를 해줘야 합니다.

```java
List<Car> cars = getCars();

if (cars != null) {
    System.out.println("자동차를 움직일거야");
}
```

메소드를 사용하는 측에서 null에 대해 인지하지 못하거나 실수로 null 에 대한 처리를 하지 않는다면, null에 대한 방어코드가 없어 오류가 발생할 수 있습니다.

실제로 크기가 0일 경우가 거의 없어 몇년 뒤에야 오류가 발생하기도 합니다.

## 2️⃣. 빈 컬력션을 반환하는 것 보다 null을 반환하는 게 좋지 않나?

빈 컬렉션을 반환하는 것보다 null을 반환하는게 비용이 낮다는 주장도 있습니다. 하지만 주장은 2가지 측면에서 틀렸다고 할 수 있습니다.

**빠른 프로그램보단 좋은 프로그램을 만들어야 한다.** (아이템 67 : 최적화는 신중히 하라)

성능 분석결과 빈 컬렉션을 할당하는 것이 성능 저하의 주범이 아니라면 이 정도의 성능 차이는 신경쓸 수준이 아니다. 빠른 프로그램보다 좋은 프로그램을 만들어야 한다.

**새로 할당하지 않고 반환이 가능하다.**

빈 컬렉션과 배열은 굳이 새로 할당하지 않고도 반환이 가능하다. 빈 컬렉션을 반환하는 전형적인 코드는 다음과 같다.

```java
private final List<Car> cars;

public List<Car> getCars() {
    return new ArrayList<>(cars);
}
```

## 3️⃣. 빈 ‘불변’ 컬렉션을 반환해야한다

비어있는 컬렉션을 매번 반환하는 것이 성능을 저하시킬 가능성은 낮지만, 사용하는 패턴에 따라 성능이 급격히 낮아질 수도 있다. 이럴땐 매번 똑같은 불변 컬렉션을 반환하면 된다. 불변객체는 공유해도 안전하다.

```java
Collections.emptyList();
Collections.emptySet();
Collections.emptyMap();
Collections.emptyEnumeration();
Collections.emptyIterator();
Collections.emptyListIterator();
Collections.emptySortedSet();
Collections.emptySortedMap();
Collections.emptyNavigableSet();
Collections.emptyNavigableMap();
```

이 역시 최적화에 해당하니 꼭 필요할때만 사용한다. 최적화가 필요하다면 성능을 측정하여 실제로 성능이 개선되는지 확인해봐야 한다.

```java
public List<Car> getCars() {
    if (cars.isEmpty()) {
        return Collections.emptyList();
    }
    return new ArrayList<>(cars);
}
```

## 4️⃣. 배열을 사용해도 null을 반환하지 않는다.

배열을 반환할때도 null을 반환하지 말고 0을 반환한다.

```java
public Car[] getCars() {
    return cars.toArray(new Car[0]);
}
```

만약 이 방식이 성능을 떨어트린다면 길이가 0인 배열을 미리 선언해두고 매번 선언한 배열을 반환하면 된다. 길이가 0인 배열은 불변하다.

```java
private static final Car[] EMPTY_CARS = new Car[0];

public Car[] getCars() {
    return cars.toArray(EMPTY_CARS);
}
```

**toArray() 메소드는 주어진 배열(EMPTY_CARS)의 크기가 충분히 크면 주어진 배열에 원소를 담아서 반환하고 그렇지 않다면 새로 만들어 그 안에 원소를 담아 반환**한다. 위와 같은 코드는 원소가 하나라도 있다면 Car[] 타입의 배열을 새로 생성해 반환하고, 원소가 0개면 EMPTY_CARS를 반환한다. 테스트 코드는 다음과 같다.

```java
@Test
void test() {
    List<Car> cars = List.of(new Car("jjong01"), new Car("jjong02"));
    Car[] result = cars.getCars(EMPTY_CARS);

    assertThat(result.length).isEqualTo(2);
}
```