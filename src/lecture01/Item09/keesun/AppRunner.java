package lecture01.Item09.keesun;

public class AppRunner {
    public static void main(String[] args) {
        try(MyResource myResource = new MyResource()) {
            myResource.doSomething();
        }
    }
}
