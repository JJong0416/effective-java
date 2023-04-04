package letcure03.Item13;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class Main {

    @Test
    @DisplayName("객체 복사")
    public void 객체_복사() throws CloneNotSupportedException {
        User user = new User("jayden-lee");
        User user2 = (User) user.clone();
        Assert.assertEquals(user.getName(), user2.getName());
    }
}
