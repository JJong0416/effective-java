package lecture_01.Item05;

import java.util.List;
import java.util.Objects;

public class BestSpellChecker {

    private final Lexicon dictionary;

    public BestSpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(String word) {
        return false;
    }

    public List<String> suggestions(String typo) {
        return null;
    }
}
