package lecture_01.item02;

import java.util.EnumSet;
import java.util.Locale;
import java.util.Set;

public abstract class Hamburger {

    public enum Topping {CHEESE, TOMATO, ONION, LETTUCE}

    final Set<Topping> toppings;

    Hamburger(Builder<?> builder) {
        toppings = builder.toppings;
    }

    abstract static class Builder<T extends Builder<T>> { // `재귀적인 타입 매개변수`

        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(topping);
            return self();
        }

        abstract Hamburger build(); // `Convariant 리턴 타입`을 위한 준비작업

        protected abstract T self(); // `self-type` 개념을 사용해서 메소드 체이닝이 가능케 함
    }
}
