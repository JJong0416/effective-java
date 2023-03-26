package lecture_01.Item05;

import java.util.List;

public class WorstSpellChecker {
    private static final Lexicon dictionary = new KoreanDictionary();

    private WorstSpellChecker(){
        // NonInstantiable
    }

    public static boolean isValid(String word){
        throw new UnsupportedOperationException();
    }

    public static List<String> suggestions(String typo) {
        throw new UnsupportedOperationException();
    }
}
