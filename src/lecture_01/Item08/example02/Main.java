package lecture_01.Item08.example02;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        KakaoBank bank = null;

        try {
            bank = new BankAttack(500);
            bank.transfer(1000);
        } catch (Exception e) {
            System.out.println("예외 발생");
        }
        System.gc();
        sleep(3000);
    }
}
