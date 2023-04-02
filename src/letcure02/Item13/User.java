package letcure02.Item13;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Stack;

public class User implements Cloneable {

    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}