package refactor;

import refactor.lotteryResult.LotteryResult;
import refactor.userLotto.LottoTicket;

public class LottoGame {
    // LotteryResult 핵심 로직 (결과를 반환하는 로직)
    public static LotteryResult getLotteryResult(LottoTicket lottoTicket, GeneratedLotto generatedLotto) {
        return LotteryResult.findLotteryResult(lottoTicket, generatedLotto);
    }
    // 일치하는 번호의 수
    public static int compareLotto(LottoTicket lottoTicket, GeneratedLotto generatedLotto) {
        return lottoTicket.findSameNumberWithSameDigit(generatedLotto);
    }

    // 보너스 번호를 가지고 있는가
    public static boolean compareBonus(LottoTicket lottoTicket, GeneratedLotto generatedLotto) {
        return lottoTicket.hasBonusInLotto(generatedLotto);
    }


}
