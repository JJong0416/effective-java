package lecture01.Item08.example01;

public class Account {

    private String name;

    public Account(String name) {
        this.name = name;
        if (this.name.equals("푸틴")){
            throw new IllegalArgumentException("푸틴은 안돼~");
        }
    }

    public void transfer(int amount, String to){
        System.out.printf("transfer %d from %s to %s.", amount, this.name, to);
    }
}
