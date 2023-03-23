package lecture_01.item01;

import java.util.EnumSet;

public class Foo {

    private String name;

    public Foo(){
    }

    private static final Foo GOOD_NIGHT = new Foo();

    public static Foo getFoo(){
        return GOOD_NIGHT;
    }

    public static Foo getFoo(boolean flag){
        Foo foo = new Foo();

        // 어떤 특정 약속 되어 있는 텍스트 파일에서
        // Foo의 구현체의 FQCN(Full Qualified Class Name)을 읽어온다.
        // 그 다음에 FQCN에 해당하는 인스턴스를 생성한다.
        // foo 변수를 해당 인스턴스를 가리키도록 수정한다.
        return foo;
    }


    public Foo(String name) {
        this.name = name;
    }

    public static Foo withName(String name){
        return new Foo("JongChan");
    }

    static class BarFoo extends Foo{

    }



    public static void main(String[] args) {
//        Foo foo = new Foo("JongChan");
        Foo foo1 = Foo.withName("JongChan");
        Foo foo2 = Foo.getFoo();
        Foo foo3 = Foo.getFoo(false);
        EnumSet<Color> colors = EnumSet.allOf(Color.class);
    }

    enum Color {
        RED, BLUE, WHITE
    }
}
