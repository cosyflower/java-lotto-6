package refactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactor.lotteryResult.LotteryResult;
import refactor.lotteryResult.LottoReceipt;
import refactor.lottoGenerator.GeneratedLotto;
import refactor.lottoGenerator.LottoRepository;
import refactor.userLotto.Bonus;
import refactor.userLotto.LottoNumbers;
import refactor.userLotto.LottoTicket;

public class ReLottoGameTest {
    private LottoRepository lottoRepository = new LottoRepository();

    private static Stream<Arguments> generateLottoNumbersAndBonusNumberValue(){
        return Stream.of(
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 11, 2_000_000_000),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 40), 6, 30_000_000),
            Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 40), 20, 1500_000)
        );
    }

    @ParameterizedTest
    @MethodSource("generateLottoNumbersAndBonusNumberValue")
    void 당첨금액_합계_테스트(List<Integer> lottoNumbers, int bonusNumberValue, int amountTotal) {
        LottoTicket lottoTicket = new LottoTicket(
                new LottoNumbers(lottoNumbers),
                new Bonus(bonusNumberValue));
        GeneratedLotto generatedLotto = new GeneratedLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        lottoRepository.addLotto(generatedLotto);

        LottoReceipt lottoReceipt = LottoGame.getLottoReceipt(lottoTicket, lottoRepository);
        EnumMap<LotteryResult, Integer> receipt = lottoReceipt.getReceipt();

        assertThat(lottoReceipt.getPrizeAmount()).isEqualTo(amountTotal);

    }
}
