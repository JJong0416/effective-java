package lecture04.Item17;

public final class Money {
    private final int value;

    private Money(int value){
        this.value = value;
    }

    public static Money valueOf(int value){
        return new Money(value);
    }

    public int getValue(){
        return this.value;
    }
}
