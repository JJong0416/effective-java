package lecture01.Item05.next_step.before;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoNumberGenerator {
    private static final List<Integer> LOTTO_TOTAL_NUMBERS = IntStream.range(1,45)
            .boxed()
            .collect(Collectors.toList());

    private AutoLottoNumberGenerator(){
        // NonInstantiable
    }

    public static List<Integer> generate(int size){
        List<Integer> lottoTotalNumbers = new ArrayList<>(LOTTO_TOTAL_NUMBERS);
        Collections.shuffle(lottoTotalNumbers);

        return lottoTotalNumbers.stream()
                .limit(size)
                .collect(Collectors.toList());
    }
}
