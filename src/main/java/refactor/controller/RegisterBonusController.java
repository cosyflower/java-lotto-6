package refactor.controller;

import refactor.dto.BonusRequest;
import refactor.exceptionHandler.ExceptionHandler;
import refactor.userLotto.Bonus;
import refactor.view.InputView;

public class RegisterLottoNumberController {
    public void process() {
        ExceptionHandler.handleException(this::generateBonus);
    }

    private Bonus generateBonus() {
        BonusRequest bonusRequest = new BonusRequest(InputView.registerLottoNumbers());
        return bonusRequest.toBonus();
    }

}
