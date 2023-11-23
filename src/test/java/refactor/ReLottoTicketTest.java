package refactor;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReLottoTicketTest {
    @Test
    void 같은_로또_번호_그리고_보너스_번호_검증() {
        LottoTicket lottoTicket = new LottoTicket(
                new LottoNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)),
                new Bonus(7));
        GeneratedLotto generatedLotto = new GeneratedLotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        int comparedLottos = LottoGame.compareLotto(lottoTicket, generatedLotto);
        boolean hasSameBonus = LottoGame.compareBonus(lottoTicket, generatedLotto);

        Assertions.assertThat(comparedLottos).isEqualTo(6);
        Assertions.assertThat(hasSameBonus).isEqualTo(false);
    }
}
