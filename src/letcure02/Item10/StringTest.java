package letcure02.Item10;


import java.awt.*;

public class StringTest {
    public static void main(String[] args) {
        CaseInsensitiveString caseString = new CaseInsensitiveString("Test");
        String test = "test";

        System.out.println(caseString.equals(test));
        System.out.println(test.equals(caseString));



    }
}

