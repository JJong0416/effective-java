package lecture_01.Item08.example02;

public class KakaoBank {
    private int money;

    public KakaoBank(final int money) {
        if (money < 1000) {
            throw new RuntimeException("1000원 이하로 생성이 불가능합니다.");
        }
        this.money = money;
    }

    void transfer(final int money) {
        this.money -= money;
        System.out.println(money  + "원 입금 완료되었습니다.");
    }
}
