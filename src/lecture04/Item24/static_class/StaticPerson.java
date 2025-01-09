package lecture04.Item24.static_class;

public class StaticPerson {
    private String name = "jjong0416";

    // 열거 타입도 암시적 static
    public enum Kinds {
        MAN, WOMAN
    }

    public static class PublicSample {
        private int temp;

        public void method() {
            StaticPerson outerClass = new StaticPerson();
            System.out.println("public" + outerClass.name); // 바깥 클래스인 Person 의 private 멤버 접근
        }
    }

    private static class PrivateSample {
        private int temp;

        public void method() {
            StaticPerson outerClass = new StaticPerson();
            System.out.println("private" + outerClass.name); // 바깥 클래스인 Person 의 private 멤버 접근
        }
    }
}