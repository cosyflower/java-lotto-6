package refactor;

import camp.nextstep.edu.missionutils.Console;
import refactor.controller.RegisterBonusController;
import refactor.view.InputView;

public class MainApplication {
    public static void main(String[] args) {
        new RegisterBonusController(new InputView(Console::readLine, System.out::println)).process();
    }
}
