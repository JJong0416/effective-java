package lecture01.item03;

public class SingletonTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Singleton01 singleton01 = Singleton01.INSTANCE;
        Singleton02 singleton02 = Singleton02.getInstance();


    }
}
