package refactor.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class InputView {
    private final Supplier<String> inputResolver; // .get() 해서 얻기
    private final Consumer<String> outputResolver; // .

    public InputView(Supplier<String> inputResolver, Consumer<String> outputResolver) {
        this.inputResolver = inputResolver;
        this.outputResolver = outputResolver;
    }

    public String registerBonusNumber() {
        print("보너스 번호를 입력해주세요 : ");
        return Console.readLine();
    }

    private void print(String message) {
        outputResolver.accept(message);
    }
}
