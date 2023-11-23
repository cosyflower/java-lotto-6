package refactor;

import static org.assertj.core.api.Assertions.assertThatCode;
import static refactor.lotteryResult.LotteryResult.FIFTH;
import static refactor.lotteryResult.LotteryResult.FIRST;
import static refactor.lotteryResult.LotteryResult.FOURTH;
import static refactor.lotteryResult.LotteryResult.SECOND;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactor.lotteryResult.LotteryResult;
import refactor.userLotto.Bonus;
import refactor.userLotto.LottoNumbers;
import refactor.userLotto.LottoTicket;

public class ReLotteryResultTest {
    private static Stream<Arguments> generateLottoTicketAndBonus() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 10, FIRST),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), 6, SECOND),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 6, 7), 15, FOURTH),
                Arguments.of(Arrays.asList(1, 2, 3, 5, 6, 7), 18, FIFTH)
        );
    }

    @ParameterizedTest(name = "로또 결과 : {2}")
    @MethodSource("generateLottoTicketAndBonus")
    void 로또_결과_검증(List<Integer> lottoTicketNumbers, int bonusNumberValue, LotteryResult expectedLotteryResult) {
        // LotteryResult를 비교한다
        // LottoTicket, GeneratedLotto 간 비교를 진행한다
        GeneratedLotto generatedLotto = new GeneratedLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket lottoTicket = new LottoTicket(
                new LottoNumbers(lottoTicketNumbers),
                new Bonus(bonusNumberValue));

        LotteryResult result = LottoGame.getLotteryResult(lottoTicket, generatedLotto);

        Assertions.assertThat(result).isEqualTo(expectedLotteryResult);
    }

    private static Stream<Arguments> generateWrongLottoTicketAndBonus() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 3, 4, 5, 6, 7), 10),
                Arguments.of(Arrays.asList(2, 3, 4, 5, 6, 7), 10),
                Arguments.of(Arrays.asList(1, 40, 41, 42, 43, 44), 18)
        );
    }

    @ParameterizedTest(name = "예외 발생")
    @MethodSource("generateWrongLottoTicketAndBonus")
    void 존재하지_않는_결과면_예외(List<Integer> lottoTicketNumbers, int bonusNumberValue) {
        GeneratedLotto generatedLotto = new GeneratedLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoTicket lottoTicket = new LottoTicket(
                new LottoNumbers(lottoTicketNumbers),
                new Bonus(bonusNumberValue));

        assertThatCode(() -> LottoGame.getLotteryResult(lottoTicket, generatedLotto))
                 .isInstanceOf(IllegalArgumentException.class);
    }
}
