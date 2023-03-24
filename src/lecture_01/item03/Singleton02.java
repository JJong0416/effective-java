package lecture_01.item03;

public class Singleton02 {
    private static final Singleton02 INSTANCE = new Singleton02();

    private Singleton02(){

    }

    public static Singleton02 getInstance(){
        return INSTANCE;
    }
}
