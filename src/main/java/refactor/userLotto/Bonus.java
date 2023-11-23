package refactor.userLotto;

import refactor.exception.LottoException;

public class Bonus {
    private final int bonusNumber;

    public Bonus(int bonusNumber) {
        validateBonusNumber(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumber(int bonusNumber) {
        isValidRange(bonusNumber);
    }

    private void isValidRange(int bonusNumber) {
        if (isNotInRange(bonusNumber)) {
            throw new LottoException("범위에서 벗어난 보너스 번호입니다.");
        }
    }

    private boolean isNotInRange(int bonusNumber) {
        return bonusNumber < 1 || bonusNumber > 45;
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }
}
