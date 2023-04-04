package letcure03.Item13;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CopyFactory {
    private String name;
    private int age;
    private Set<String> subjects;

    public CopyFactory(String name, int age, Set<String> subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    // 복사 생성자
    public CopyFactory(CopyFactory copyFactory) {
        this.name = copyFactory.name;
        this.age = copyFactory.age;
        this.subjects = new HashSet<>(copyFactory.subjects);    // 딥 카피
    }

    // 팩토리 복사
    public static CopyFactory newInstance(CopyFactory copyFactory) {
        return new CopyFactory(copyFactory);
    }

    @Override
    public String toString() {
        return Arrays.asList(name, String.valueOf(age),
                subjects.toString()).toString();
    }

    public Set<String> getSubjects() {
        return subjects;
    }
}