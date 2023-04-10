package lecture04.Item18;

public class WinningCompositionLotto {

    private final BonusBall bonusBall;
    private final Lotto lotto;

    public WinningCompositionLotto(BonusBall bonusBall, Lotto lotto) {
        this.bonusBall = bonusBall;
        this.lotto = lotto;
    }

    public long compare(Lotto lotto) {
        return lotto.lottoNumbers.stream()
                .filter(lotto::contains)
                .count();
    }
}
