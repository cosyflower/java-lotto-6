package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.HashSet;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.generator.MockedRandomGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoGameTest {
    private LottoGame lottoGame = new LottoGame();

    public static int generateRandomNumberInRange(int startInclusive, int endInclusive) {
        return Randoms.pickNumberInRange(startInclusive, endInclusive);
    }

    @Test
    void 로또_번호_조건_만족_여부_검증() {
        Lotto generatedLotto = lottoGame.generateLotto(new MockedRandomGenerator());

        List<Integer> lottoNumbers = generatedLotto.getLotto();

        Assertions.assertThat(lottoNumbers.size()).isEqualTo(6);
        Assertions.assertThat(lottoNumbers.size()).isEqualTo(
                new HashSet<Integer>(lottoNumbers).size());
        Assertions.assertThat(
                lottoNumbers.stream()
                        .filter(number -> number > 45 || number < 1)
                        .findFirst()
                        .isPresent()).isEqualTo(false);
    }
}