package lecture_01.Item08.example02;

public class BankAttack extends KakaoBank{
    public BankAttack(int money) {
        super(money);
    }

    @Override
    protected void finalize() throws Throwable{
        this.transfer(10000000);
    }
}
