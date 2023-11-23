package refactor.controller;

import refactor.dto.BonusRequest;
import refactor.exceptionHandler.ExceptionHandler;
import refactor.userLotto.Bonus;
import refactor.view.InputView;

public class RegisterBonusController {
    private final InputView inputView;

    public RegisterBonusController(InputView inputView) {
        this.inputView = inputView;
    }

    public void process() {
        ExceptionHandler.handleException(this::generateBonus); // try - catch 할 구간만 선택적으로 적용이 가능하다
    }

    private Bonus generateBonus() {
        BonusRequest bonusRequest = new BonusRequest(inputView.registerBonusNumber());
        return bonusRequest.toBonus();
    }

}
