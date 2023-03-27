package lecture_01.Item08.example01;

public class BrokenAccount extends Account{

    public BrokenAccount(String name) {
        super(name);
    }

    @Override
    protected void finalize() throws Throwable{
        this.transfer(10000, "sura");
    }
}
