package lotto.dto.input;

import lotto.domain.Lotto;
import lotto.util.Parser;
import lotto.util.validator.NumberValidator;

public class UserLottoDTO {
    private final String inputLotto;

    public UserLottoDTO(String inputLotto) {
        validateLotto(inputLotto);
        this.inputLotto = inputLotto;
    }

    private void validateLotto(String inputLotto) {
        NumberValidator.isNullOrEmpty(inputLotto);
        NumberValidator.isLottoPattern(inputLotto);
        NumberValidator.startsWithZero(inputLotto);
    }

    public Lotto toLotto() {
        // inputLotto -> List<Integer> 로 변환해야 한다
        return Lotto.from(Parser.inputToLottoNumbers(inputLotto));
    }
}
