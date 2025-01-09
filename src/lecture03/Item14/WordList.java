package lecture03.Item14;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class WordList {
    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        Collections.addAll(s, args);
        System.out.println(s);
    }

    public int AntiCompareTo(int x, int y) {
        return x < y ? 1 : (x == y) ? 0 : -1;
    }

    static Comparator<Object> hashCodeOrder =
            Comparator.comparingInt(o -> o.hashCode());

}
