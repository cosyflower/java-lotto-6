package refactor.dto;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class ReLottoNumbersRequestTest {
    @Nested
    class LottoNumberRequestCase {
        @ParameterizedTest
        @NullAndEmptySource
        void 아무것도_입력하지_않으면_예외(String nothingInput) {
            assertThatCode(() -> new LottoNumbersRequest(nothingInput))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("아무것도 입력하지 않았습니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,2,뮤,!", "!@#$df", "잘못된 로또번호"})
        void 로또_패턴에_맞지_않으면_예외(String notMatchedLottoPattern) {
            assertThatCode(() -> new LottoNumbersRequest(notMatchedLottoPattern))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 패턴이 아닙니다.");
        }

        @Test
        void Request_성공() {
            assertThatCode(() -> new LottoNumbersRequest("1,2,3,4,5,6"))
                    .doesNotThrowAnyException();
        }
    }

    @Nested
    class LottoNumberCase {
        @ParameterizedTest
        @ValueSource(strings = {"1,2,3,4", "1,2,3", "1,2"})
        void 로또_번호의_개수가_6개가_아니면_예외(String unvalidSizeInput) {
            assertThatCode(() -> new LottoNumbersRequest(unvalidSizeInput).toLottoNumbers())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호의 개수가 올바르지 않습니다.");
        }

        @ParameterizedTest
        @ValueSource(strings = {"0,1,2,3,4,5", "1,2,3,4,5,46"})
        void 범위에_벗어나면_예외(String outOfRangeInput) {
            assertThatCode(() -> new LottoNumbersRequest(outOfRangeInput).toLottoNumbers())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("로또 번호가 범위에 맞지 않습니다");
        }

        @ParameterizedTest
        @ValueSource(strings = {"1,2,2,2,2,2", "1,2,3,4,5,5", "1,1,1,1,1,1"})
        void 번호가_중복되면_예외(String duplicatedInput) {
            assertThatCode(() -> new LottoNumbersRequest(duplicatedInput).toLottoNumbers())
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("중복된 로또 번호입니다.");
        }

        @Test
        void 조건에_만족하면_성공() {
            assertThatCode(() -> new LottoNumbersRequest("10,20,30,4,5,6").toLottoNumbers())
                    .doesNotThrowAnyException();
        }
    }
}
