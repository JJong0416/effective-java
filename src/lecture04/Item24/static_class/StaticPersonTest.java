package lecture04.Item24.static_class;

import org.junit.jupiter.api.Test;

public class StaticPersonTest {
    @Test
    void test() {
        StaticPerson staticPerson = new StaticPerson();

        // Enum 접근 가능
        StaticPerson.Kinds man = StaticPerson.Kinds.MAN;
        StaticPerson.Kinds woman = StaticPerson.Kinds.WOMAN;

        // public static 클래스 레벨에서 접근 가능
        System.out.println(new StaticPerson.PublicSample());
        // private static 클래스 레벨에서 접근 불가능
        // System.out.println(new Person.PrivateSample());
    }
}
