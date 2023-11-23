package refactor.lotteryResult;

import static refactor.lotteryResult.RankValue.FIVE;
import static refactor.lotteryResult.RankValue.FOUR;
import static refactor.lotteryResult.RankValue.SIX;
import static refactor.lotteryResult.RankValue.THREE;

import java.util.Arrays;
import java.util.function.BiPredicate;
import refactor.lottoGenerator.GeneratedLotto;
import refactor.LottoGame;
import refactor.userLotto.LottoTicket;

public enum LotteryResult {
    FIRST((lottoTicket, lotto) -> LottoGame.compareLotto(lottoTicket, lotto) == SIX.getSameNumberCount()),
    SECOND((lottoTicket, lotto) -> (LottoGame.compareLotto(lottoTicket, lotto) == FIVE.getSameNumberCount()) &&
            LottoGame.compareBonus(lottoTicket, lotto)),
    THIRD((lottoTicket, lotto) -> LottoGame.compareLotto(lottoTicket, lotto) == FIVE.getSameNumberCount()),
    FOURTH((lottoTicket, lotto) -> LottoGame.compareLotto(lottoTicket, lotto) == FOUR.getSameNumberCount()),
    FIFTH((lottoTicket, lotto) -> LottoGame.compareLotto(lottoTicket, lotto) == THREE.getSameNumberCount())
    ;

    private final BiPredicate<LottoTicket, GeneratedLotto> rule;

    LotteryResult(BiPredicate<LottoTicket, GeneratedLotto> rule) {
        this.rule = rule;
    }

    public static LotteryResult findLotteryResult(LottoTicket lottoTicket, GeneratedLotto generatedLotto) {
        return Arrays.stream(values())
                .filter(lotteryResult -> lotteryResult.hasAppliedCondition(lottoTicket, generatedLotto))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean hasAppliedCondition(LottoTicket lottoTicket, GeneratedLotto generatedLotto) {
        return rule.test(lottoTicket, generatedLotto);
    }
}
