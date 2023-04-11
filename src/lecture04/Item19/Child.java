package lecture04.Item19;

class Child extends Parent {
    private final int value;

    public Child(int value) {
        this.value = value;
        System.out.println("Child constructor : " + value);
    }

    @Override
    public void initialize() {
        System.out.println("Child initialize with value: " + value);
    }
}