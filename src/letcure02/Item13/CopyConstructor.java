package letcure02.Item13;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CopyConstructor {
    private String name;
    private int age;
    private Set<String> subjects;

    public CopyConstructor(String name, int age, Set<String> subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    // 복사 생성자
    public CopyConstructor(CopyConstructor copyConstructor) {
        this.name = copyConstructor.name;
        this.age = copyConstructor.age;

        // 얕은 복사
        // this.subjects = student.subjects;

        // 깊은 복사 – `HashSet`의 새 인스턴스 생성
        this.subjects = new HashSet<>(copyConstructor.subjects);
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