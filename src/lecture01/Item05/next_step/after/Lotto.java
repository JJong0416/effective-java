package lecture01.Item05.next_step.after;

import lecture01.Item05.next_step.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private static final int DEFAULT_LOTTO_NUMBERS_SIZE = 6;

    private final Set<LottoNumber> lottoNumbers;

    public Lotto(LottoNumberGenerator lottoNumberGenerator){
        List<Integer> numbers = lottoNumberGenerator.generate(DEFAULT_LOTTO_NUMBERS_SIZE);
        this.lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    public Set<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableSet(lottoNumbers);
    }
}
