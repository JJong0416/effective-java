package lecture_01.Item05.next_step.after;

import java.util.List;

@FunctionalInterface
public interface LottoNumberGenerator {
    List<Integer> generate(int size);
}
