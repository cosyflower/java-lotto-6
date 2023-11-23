package refactor;

import java.util.Arrays;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import refactor.lottoGenerator.GeneratedLotto;
import refactor.userLotto.Bonus;

public class ReBonusNumberTest {
    @ParameterizedTest
    @CsvSource(value = {"5:true", "6:true", "7:false"} , delimiter = ':')
    void 보너스_번호_포함여부_검증(int bonusNumberValue, boolean expectedResult) {
        GeneratedLotto generatedLotto = new GeneratedLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Bonus bonus = new Bonus(bonusNumberValue);

        boolean result = generatedLotto.hasSameNumber(bonus);
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }
}
