package refactor.lotteryResult;

import static refactor.lotteryResult.RankValue.FIVE;
import static refactor.lotteryResult.RankValue.FOUR;
import static refactor.lotteryResult.RankValue.SIX;
import static refactor.lotteryResult.RankValue.THREE;

import java.util.Arrays;
import java.util.function.BiPredicate;
import refactor.LottoGame;
import refactor.lottoGenerator.GeneratedLotto;
import refactor.userLotto.LottoTicket;

public enum LotteryResult {
    FIRST((lottoTicket, lotto) -> LottoGame.compareLotto(lottoTicket, lotto) == SIX.getSameNumberCount(),
            2_000_000_000),
    SECOND((lottoTicket, lotto) -> (LottoGame.compareLotto(lottoTicket, lotto) == FIVE.getSameNumberCount()) &&
            LottoGame.compareBonus(lottoTicket, lotto) , 30_000_000),
    THIRD((lottoTicket, lotto) -> LottoGame.compareLotto(lottoTicket, lotto) == FIVE.getSameNumberCount(),
            1500_000),
    FOURTH((lottoTicket, lotto) -> LottoGame.compareLotto(lottoTicket, lotto) == FOUR.getSameNumberCount(),
            50_000),
    FIFTH((lottoTicket, lotto) -> LottoGame.compareLotto(lottoTicket, lotto) == THREE.getSameNumberCount(),
            5_000)
    ;

    private final BiPredicate<LottoTicket, GeneratedLotto> rule;
    private final int LotteryAmount;

    LotteryResult(
            BiPredicate<LottoTicket, GeneratedLotto> rule, int lotteryAmount) {
        this.rule = rule;
        LotteryAmount = lotteryAmount;
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

    public int getLotteryAmount() {
        return LotteryAmount;
    }
}
