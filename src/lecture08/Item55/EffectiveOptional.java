package lecture08.Item55;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class EffectiveOptional {
    public static String prevFindSomething(List<String> list) {
        if (list.isEmpty()) {
            return null;
        }

        for (String s : list) {
            if (s.equals("something")) {
                return s;
            }
        }
        return null;
    }

    public static Optional<String> advFindSomething(List<String> list) {
        if (list.isEmpty()){
            return Optional.empty();
        }

        for (String s : list) {
            if (s.equals("something")){
                return Optional.of("hello");
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        List<String> test = new LinkedList<>();

        Optional<String> s = advFindSomething(test);

        Optional<String> something = advFindSomething(List.of("something"));
        String find1 = something.orElse(nothing());
        String find2 = something.orElseGet(() -> nothing());
        // == String find3 = something.orElseGet(EffectiveOptional::nothing);

        OptionalInt optionalInt = OptionalInt.of(0);
        System.out.println(optionalInt.isPresent());

        if (advFindSomething(List.of("something")).isPresent()) {
            System.out.println("찾았다.");
        } else {
            System.out.println("못 찾았다.");
        }


    }

    static String nothing() {
        System.out.println("나 생성되었어");
        return "No";
    }
}
