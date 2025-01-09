> REFERENCE:
>

[https://github.com/woowacourse-study/2022-effective-java](https://github.com/woowacourse-study/2022-effective-java)

[Item 14. Comparable을 구현할지 고려하라 | Carrey`s 기술블로그](https://jaehun2841.github.io/2019/01/13/effective-java-item14/)

[item 14. Comparable을 구현할지 고려하라 Consider Implementing Comparable](https://sihyung92.oopy.io/java/effective-java/14)

---

## 1️⃣. CompareTo 메소드란?

Comparable 인터페이스의 유일한 메소드이다. equals처럼 동작하지만, 추가적으로 순서까지 비교할 수 있다.

- 반환값이 1이면 더 큰 값
- 반환값이 0이면 동일한 값
- 반환값이 -1이면 더 작은값을 말한다.

Comparable을 구현했다는 것은 클래스의 인스턴스들 간에 자연적인 순서를 가져야함을 의미한다.

그렇기에 Comparable을 구현한 객체의 배열은 손쉽게 정렬할 수 있다.

```java
...
int[] numbers = new int[]{32,11,15,86};
Arrays.sort(numbers);
```

## 2️⃣. Compareable의 힘

Comparable을 구현하면 이 인터페이스를 활용하는 **수 많은 제네릭 알고리즘과 컬렉션의 힘을 누릴 수 있게** 됩니다. 알파벳, 숫자, 연대 같은 순서가 명확한 값 클래스를 작성한다면 반드시 Comparable 인터페이스를 구현해야한다.

검색, 극닥값 계산, 자동 정렬되는 컬렉션 관리도 역시 쉽게 할 수 있다. 예를 들어보면, 다음 프로그램은 명령줄 인수들을 (중복을 제거하고) 알파벳 순으로 출력한다.

```java
public class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, args);
        System.out.println(s);
    }
}
```

## 3️⃣. CompareTo의 규약

1. 반사성
    - 두 객체 참조의 순서를 바꿔 비교해도 예상된 결과가 나와야 합니다.
    - x.compareTo(y)가 예외를 던진다면, y.compareTo(y)도 예외를 던져야 합니다.
2. 추이성
    - 첫 번째보다 두 번째 객체가 크고, 두 번째 객체가 세 번째 객체보다 크면, 세 번쨰 객체는 첫번쨰 객체보다 커야 합니다.
    - x, y, z에 대해 x.compareTo(y) > 0 이고 y.compareTo(z)이면, x.compareTo(z)를 만족해야 한다.
3. 대칭성
    - 크기가 같은 객체들끼리는 어떤 객체와 비교하더라도 항상 같아야 합니다.
4. **equals(권고)**
    - Comparable을 구현한 클래스는 모든 x, y에 대해 x.compareTo(y) == 0 이면, x.equals(y)를 만족하는 것이 좋다.
    - 이 권고를 지키지 않으려면, `주의: 이 클래스의 순서는 equals 메서드와 일관되지 않다.`라고 명시해 주자.

## 4️⃣. equals와 compareTo의 차이점

new Decimal(“1.0”)과 new Decimal(“1.00”)이 있다고 할 때 두 객체를 HashSet에 담게 되면 size는 2개가 된다. **하지만 TreeSet에 담게 되면 size는 1개가 된다.**

왜 이런 결과가 나올까? HashSet에서는 **equals를 기반으로 비교**하기 때문에 new Decimal(“1.0”)과 new Decimal(“1.00”)은 서로 다른 객체이다. 그렇기 때문에 size가 2개가 된다.

하지만 TreeSet에서는 객체에 대한 동치성 비교를 **compareTo로 하기 때문**에 new Decimal(“1.0”)과 new Decimal(“1.00”)의 compareTo는 0을 리턴한다.따라서 같은 객체로 인식하여 size가 1개가 된다.

## 5️⃣. compareTo 안티패턴

compareTo 메소드에서 관계연산자 ( ‘<’ 와 ‘>’)를 사용하지 말아야 한다.

대신 Type.compare(T t1, T t2)를 사용하여 비교하는 것이 좋다.

### A) 안티 패턴

```java
public int AntiCompareTo(int x, int y) {
    return x < y ? 1 : (x == y) ? 0 : -1;
}
```

hashCode의 차를 이용한 비교 또한 추이성을 위배하게 된다.

```java
static Comparator<Object> hashCodeOrder = new Comparator<>() {
  (Object o1, Object o2) -> o1.hashCode() - o2.hashCode();
}
```

### B) 올바른 패턴

위 코드처럼 실행하게 되면 정수 overflow를 일으키거나 IEEE754 부동소수점 계산 방식에 따른 오류를 발생 시킬 수 있습니다. 따라서 아래 코드로 고쳐 사용하는 것이 좋습니다.

```java
static Comparator<Object> hashCodeOrder = new Comparator<>() {
  (Object o1, Object o2) -> Integer.compare(o1.hashCode(), o2.hashCode())
}
```

```java
static Comparator<Object> hashCodeOrder = 
													Comparator.comparingInt(o -> o.hashCode());
```

## 5️⃣. Comparable과 Comparator의 차이는 무엇일까요?

### A) Comparable

- 자기 자신과 매개변수 객체를 비교
- 주로 정렬해야 하는 클래스를 구현체로 만들어서 사용
- lang 패키지 안에 있으므로 import를 따로 할 필요는 없다.

### B) Comparator

- 두 매개변수 객체를 비교
- 주로 익명 객체로 구현해서 사용
- util 패키지 안에 있으므로 import가 필요합니다.

## 6️⃣. Comparable을 구현 시 주의해야 할 점

Comparable은 제네릭 인터페이스이므로 입력 인수의 타입을 확인하거나 형변환 하지 않아도 된다.
내부 필드 중 객체 참조 필드는 compareTo를 재귀적으로 호출하고, Comparable을 구현하지 않은 필드는 Comparator을 대신 활용한다. Comparator는 직접 구현해도 되고 자바 라이브러리에서 재공하는 것을 사용해도 된다.
원시 타입인 경우 랩핑 클래스의 정적 메서드 compareTo를 이용하면 된다.
핵심 필드가 여럿 인 경우 핵심적인 필드부터 비교해나가자. 비교 결과가 0이 아니라면 곧장 반환하자. 핵심이 되는 필드가 동일하다면, 똑같지 않은 필드를 찾을 때 까지 비교해 나간다.

## 7️⃣. ****Comparator의 비교자 생성 메서드****

자바 8 부터는 Comparator 인터페이스가 메서드 체이닝 방식으로 Comparator를 구성할 수 있게 되었다.

```java
// Comparable with comparator construction methods
private static final Comparator<PhoneNumber> COMPARATOR =
	comparingInt((PhoneNumber pn) -> pn.areaCode)
	.thenComparingInt(pn -> pn.prefix)
	.thenComparingInt(pn -> pn.lineNum);

public int compareTo(PhoneNumber pn) {
	return COMPARATOR.compare(this, pn);
}
```