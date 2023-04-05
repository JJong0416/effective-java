package lecture04.modify;

public class SubClass extends SuperClass {
    @Override
    public void methodA() { // private 시, 컴파일 에러
        System.out.println("B!!");
    }
}
