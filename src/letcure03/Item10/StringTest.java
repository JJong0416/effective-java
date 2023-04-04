package letcure03.Item10;


public class StringTest {
    public static void main(String[] args) {
        CaseInsensitiveString caseString = new CaseInsensitiveString("Test");
        String test = "test";

        System.out.println(caseString.equals(test));
        System.out.println(test.equals(caseString));



    }
}

