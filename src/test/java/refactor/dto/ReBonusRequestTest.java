package refactor.dto;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class ReBonusRequestTest {
    @Nested
    class BonusRequsetCase {
        @ParameterizedTest
        @NullAndEmptySource
        void 아무것도_입력하지_않으면_예외(String nothingInput) {
            assertThatCode(() -> new BonusRequest(nothingInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("아무것도 입력하지 않았습니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"뮤", "!@#$"})
        void 수가_입력되지_않으면_예외(String notMatchedNumberPattern) {
            assertThatCode(() -> new BonusRequest(notMatchedNumberPattern))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("수를 입력하지 않았습니다.");
        }

        @Test
        void 수를_입력하면_성공() {
            assertThatCode(() -> new BonusRequest("50"))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    class BonusCase {
        @ParameterizedTest
        @ValueSource(strings = {"0", "46"})
        void 범위에서_벗어나면_예외(String outOfRangeInput) {
            assertThatCode(() -> new BonusRequest(outOfRangeInput).toBonus())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("범위에서 벗어난 보너스 번호입니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1", "45"})
        void 범위에_맞으면_성공() {
            assertThatCode(() -> new BonusRequest("45").toBonus())
                    .doesNotThrowAnyException();
        }
    }

}
