package lecture03.Item10;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        final Pattern P1 = Pattern.compile("//.*");
        final Pattern P2 = Pattern.compile("//.*");

        System.out.println(P1.equals(P1)); // true
        System.out.println(P1.equals(P2)); // false
        System.out.println(P1.pattern()); // //.*
        System.out.println(P1.pattern().equals(P1.pattern())); // true
        System.out.println(P1.pattern().equals(P2.pattern())); // true

    }
}
