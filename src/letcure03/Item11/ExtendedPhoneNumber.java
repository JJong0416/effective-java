package letcure03.Item11;

public class ExtendedPhoneNumber extends PhoneNumber {

    public ExtendedPhoneNumber(
            final int firstNumber,
            final int secondNumber,
            final int lastNumber
    ) {
        super(firstNumber, secondNumber, lastNumber);
    }

    @Override
    public int hashCode() {
        int c = 31;
        int hashcode = Integer.hashCode(firstNumber);
        hashcode = c * hashcode + Integer.hashCode(secondNumber);
        hashcode = c * hashcode + Integer.hashCode(lastNumber);
        return hashcode;
    }
}
