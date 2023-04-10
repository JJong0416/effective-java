package lecture04.Item18;

import java.util.List;

public class WinningExtendLotto extends Lotto {
    private final BonusBall bonusBall;

    public WinningExtendLotto(List<Integer> lottoNumbers, BonusBall bonusBall) {
        super(lottoNumbers);
        this.bonusBall = bonusBall;
    }

    public long compare(Lotto lotto) {
        return lottoNumbers.stream()
                .filter(lotto::contains)
                .count();
    }
}