package lecture04.Item19;

public class Parent {
    public Parent() {
        System.out.println("Parent constructor");
        initialize();
    }

    public void initialize() {
        System.out.println("Parent initialize");
    }
}
