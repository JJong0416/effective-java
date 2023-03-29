package lecture01.Item08.example01;

import org.junit.jupiter.api.Test;

public class AccountTest {
    @Test
    void 일반_사람(){
        Account account = new Account("jjong0416");
        account.transfer(100, "sura");
    }

    @Test
    void 러시아(){
        Account account = new Account("푸틴");
        account.transfer(100, "sura");
    }

    @Test
    void 한번_공격해보자() throws InterruptedException {
        Account account = null;

        try {
            account = new BrokenAccount("푸틴");
        } catch (Exception e){
            System.out.println("푸틴은 안되는데!?!?");
        }
        System.gc();
        Thread.sleep(3000L);
    }

}
