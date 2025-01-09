package lecture01.Item09;

public class Application {
    public static void main(String[] args) {
        try {
            check();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void check() {
        try {
            throw new IllegalArgumentException();
        } finally {
            throw new NullPointerException();
        }
    }
}
