package refactor.lotteryResult;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ReLottoReceiptTest {
    @Test
    void 결과를_추가하고_개수_검증() {
        LottoReceipt lottoReceipt = new LottoReceipt();

        lottoReceipt.addEachLotteryResult(LotteryResult.FIRST);

        assertThat(lottoReceipt.getEachLotteryResultCount(LotteryResult.FIRST)).isEqualTo(1);
        assertThat(lottoReceipt.getEachLotteryResultCount(LotteryResult.SECOND)).isEqualTo(0);
    }

    private static Stream<Arguments> generateLotteryResult() {
        return Stream.of(
                Arguments.of(LotteryResult.FIRST),
                Arguments.of(LotteryResult.SECOND),
                Arguments.of(LotteryResult.THIRD),
                Arguments.of(LotteryResult.FOURTH),
                Arguments.of(LotteryResult.FIFTH)
        );
    }

    @ParameterizedTest(name = "로또 결과 : {0}")
    @MethodSource("generateLotteryResult")
    void 당첨_금액_총합_검증(LotteryResult lotteryResult) {
        LottoReceipt lottoReceipt = new LottoReceipt();

        lottoReceipt.addEachLotteryResult(lotteryResult);

        int prizeAmount = lottoReceipt.getPrizeAmount();
        Assertions.assertThat(prizeAmount).isEqualTo(lotteryResult.getLotteryAmount());
    }

    // 총합 - 직접적인 금액으로 검증하는 경우도 추가하기
}
