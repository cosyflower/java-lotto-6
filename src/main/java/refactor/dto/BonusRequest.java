package refactor.dto;

import refactor.userLotto.Bonus;

public class BonusRequest {
    private final String inputBonus;

    public BonusRequest(String inputBonus) {
        validateInputBonus(inputBonus);
        this.inputBonus = inputBonus;
    }

    private void validateInputBonus(String inputBonus) {
        isNullOrEmpty(inputBonus);
        hasNumberPattern(inputBonus);
    }

    private void isNullOrEmpty(String inputBonus) {
        if (inputBonus == null || inputBonus.isEmpty()) {
            throw new IllegalArgumentException("아무것도 입력하지 않았습니다.");
        }
    }

    private void hasNumberPattern(String inputBonus) {
        if (RequestRegex.NUMBER_PATTERN.matcher(inputBonus).matches()) {
            return;
        }
        throw new IllegalArgumentException("수를 입력하지 않았습니다.");
    }

    public Bonus toBonus() {
        return new Bonus(Parser.convertStringToNumber(inputBonus));
    }
}
