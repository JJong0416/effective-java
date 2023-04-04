package letcure03.Item10;

public class CaseInsensitiveString {

    private final String str;

    public CaseInsensitiveString(final String str) {
        this.str = str;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CaseInsensitiveString){
            return str.equalsIgnoreCase(((CaseInsensitiveString) obj).str);
        }

        if (obj instanceof String) {
            return str.equalsIgnoreCase((String) obj);
        }
        return false;
    }
}
