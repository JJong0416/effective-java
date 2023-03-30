package letcure02.Item11;

import lombok.AccessLevel;
import lombok.Builder;

import java.util.Objects;

public class PhoneNumber {

    protected int firstNumber;
    protected int secondNumber;
    protected int lastNumber;

    @Builder(access = AccessLevel.PRIVATE)
    protected PhoneNumber(
            final int firstNumber,
            final int secondNumber,
            final int lastNumber
    ){
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.lastNumber = lastNumber;
    }

    public static PhoneNumber of(
            final int firstNumber,
            final int secondNumber,
            final int lastNumber
    ) {
        return PhoneNumber.builder()
                .firstNumber(firstNumber)
                .secondNumber(secondNumber)
                .lastNumber(lastNumber)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        // 1. == 연산자를 사용해 입력이 자기 자신의 참조인지 확인한다.
        if (this == o) {
            return true;
        }

        // 2. instanceof 연산자로 입력이 올바른 타입인지 확인한다.
        if (!(o instanceof PhoneNumber)) {
            return false;
        }

        // 3. 입력을 올바른 타입으로 형변환 후, 비교를 한다.
        PhoneNumber p = (PhoneNumber) o;
        return this.firstNumber == p.firstNumber &&
                this.secondNumber == p.secondNumber &&
                this.lastNumber == p.lastNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstNumber,secondNumber,lastNumber);
    }
}