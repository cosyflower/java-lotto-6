package refactor.userLotto;

import refactor.lottoGenerator.GeneratedLotto;

public class LottoTicket {
    private final LottoNumbers lottoNumbers;
    private final Bonus bonus;

    public LottoTicket(LottoNumbers lottoNumbers, Bonus bonus) {
        this.lottoNumbers = lottoNumbers;
        this.bonus = bonus;
    }

    public int findSameNumberWithSameDigit(GeneratedLotto generatedLotto) {
        return lottoNumbers.compareNumberWithDigit(generatedLotto);
    }

    public boolean hasBonusInLotto(GeneratedLotto generatedLotto) {
        return generatedLotto.hasSameNumber(bonus);
    }
}
