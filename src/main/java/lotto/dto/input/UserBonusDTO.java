package lotto.dto.input;

import lotto.domain.Bonus;
import lotto.util.Parser;
import lotto.util.validator.NumberValidator;

public class UserBonusDTO {
    private final String inputBonus;

    public UserBonusDTO(String inputBonus) {
        validateBonus(inputBonus);
        this.inputBonus = inputBonus;
    }

    private void validateBonus(String inputBonus) {
        NumberValidator.isNullOrEmpty(inputBonus);
        NumberValidator.isNumberPattern(inputBonus);
        NumberValidator.startsWithZero(inputBonus);
    }

    public Bonus toBonus() {
        return Bonus.from(Parser.inputToNumber(inputBonus));
    }

}
