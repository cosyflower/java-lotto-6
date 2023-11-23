package refactor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import refactor.userLotto.Bonus;
import refactor.userLotto.LottoNumbers;
import refactor.userLotto.LottoTicket;

public class ReLottoTest {
    private static Stream<Arguments> generateLottoTicket() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 5, 7), 5),
                Arguments.of(Arrays.asList(1, 2, 3, 4, 6, 7), 4),
                Arguments.of(Arrays.asList(1, 2, 3, 5, 6, 7), 3),
                Arguments.of(Arrays.asList(1, 2, 4, 5, 6, 7), 2),
                Arguments.of(Arrays.asList(1, 3, 4, 5, 6, 7), 1),
                Arguments.of(Arrays.asList(2, 3, 4, 5, 6, 7), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("generateLottoTicket")
    void 같은_자리_같은_번호_일치(List<Integer> lotto, int expectedSameNumber) {
        LottoNumbers lottoNumbers = new LottoNumbers(lotto);
        LottoTicket lottoTicket = new LottoTicket(lottoNumbers, new Bonus(20));
        GeneratedLotto generatedLotto = new GeneratedLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoGame lottoGame = new LottoGame();

        // 같은 자리 그리고 같은 번호면 일치한다
        int sameNumberTotal = lottoGame.compareLotto(lottoTicket, generatedLotto);

        assertThat(sameNumberTotal).isEqualTo(expectedSameNumber);
    }
}
