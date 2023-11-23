package refactor;

import refactor.userLotto.LottoTicket;

public class LottoGame {
    // 일치하는 번호의 수
    public static int compareLotto(LottoTicket lottoTicket, GeneratedLotto generatedLotto) {
        return lottoTicket.findSameNumberWithSameDigit(generatedLotto);
    }

    // 보너스 번호를 가지고 있는가
    public static boolean compareBonus(LottoTicket lottoTicket, GeneratedLotto generatedLotto) {
        return lottoTicket.hasBonusInLotto(generatedLotto);
    }
}
