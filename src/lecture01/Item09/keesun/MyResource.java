package lecture01.Item09.keesun;

public class MyResource implements AutoCloseable{
    public void doSomething() {
        System.out.println("Do something");
        throw new FirstException();
    }

    @Override
    public void close() throws SecondException {
        System.out.println("Close My Resource");
        throw new SecondException();
    }
}
