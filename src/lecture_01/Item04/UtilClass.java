package lecture_01.Item04;

public class UtilClass {

    // private로 생성자를 만드면 외부에서 인스턴스를 생성하려고 시도하면 컴파일
    // 오류가 발생한다.
    private UtilClass() {
        throw new AssertionError();
    }

    public static String getName(){
        return "jjong";
    }

    static class AnotherClass extends UtilClass{

    }

    public static void main(String[] args) {
        AnotherClass anotherClass = new AnotherClass();

        String name = anotherClass.getName();
//        String name = UtilClass.getName();
        System.out.println(name);
    }
}
