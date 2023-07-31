package lecture04.Item24.non_static_class;

import org.junit.jupiter.api.Test;

public class NonStaticPersonTest {

    @Test
    void test() {
        Person person = new Person();

        // Person class is not an encl
        // System.out.println(new Person.PublicSample());
    }
}
