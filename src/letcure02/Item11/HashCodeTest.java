package letcure02.Item11;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnit4.class)
public class HashCodeTest {

    @Test
    @DisplayName("해시코드를 재정의하지 않아 값을 찾지 못한다.")
    public void hashcode_재정의안하고_map비교(){
        Map<PhoneNumber, String> map = new HashMap<>();
        map.put(PhoneNumber.of(707,867,5309), "jjong0416");
        assertEquals(map.get(PhoneNumber.of(707,867,5309)), "jjong0416");
    }

    @Test
    public void hashcode_재정의() {
        HashMap<ExtendedPhoneNumber, String> map = new HashMap<>();
        map.put(new ExtendedPhoneNumber(707, 867, 5307), "제니");

        System.out.println("Instance 1 hashcode : " +
                new ExtendedPhoneNumber(707, 867, 5307).hashCode());

        System.out.println("Instance 2 hashcode : " +
                new ExtendedPhoneNumber(707, 867, 5307).hashCode());

        Assert.assertEquals(map.get(
                new ExtendedPhoneNumber(707, 867, 5307)), "제니");
    }

    @Test
    public void hashcode_재정의안함() {
        HashMap<PhoneNumber, String> map = new HashMap<>();
        map.put(PhoneNumber.of(707, 867, 5307), "제니");
        System.out.println("Instance 1 hashcode : " +
                PhoneNumber.of(707, 867, 5307).hashCode());
        System.out.println("Instance 2 hashcode : " +
                PhoneNumber.of(707, 867, 5307).hashCode());
        Assert.assertNotEquals(map.get(
                new PhoneNumber(707, 867, 5307)), "제니");
    }

    @Test
    public void hashcode_같은값_반환() {
        HashMap<ExtendedPhoneNumber, String> map = new HashMap<>();
        map.put(new ExtendedPhoneNumber(707, 867, 5307), "제니");
        System.out.println("Instance 1 hashcode : " +
                new ExtendedPhoneNumber(707, 867, 5307).hashCode());
        System.out.println("Instance 2 hashcode : " +
                new ExtendedPhoneNumber(707, 867, 5301).hashCode());
        //다른 객체를 넣어 데이터를 조회해 보았다.
        Assert.assertEquals(map.get(new ExtendedPhoneNumber(707, 867, 5301)), "제니");
    }
}
