package lecture01.Item05.next_step.before;

import lecture01.Item05.next_step.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Lotto {

    private static final int DEFAULT_LOTTO_NUMBERS_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Lotto() {
        List<Integer> numbers = AutoLottoNumberGenerator.generate(DEFAULT_LOTTO_NUMBERS_SIZE);
        this.lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(toSet());
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }
}
