package refactor;

import java.util.ArrayList;
import java.util.List;
import refactor.userLotto.Bonus;

public class GeneratedLotto {
    // 고쳐야 할 점
    // 내가 직접 수를 생성하고 있음
    // 랜덤 난수를 생성하는 방향으로 다시 설정해야 한다
    private final List<Integer> generatedNumbers;
    public GeneratedLotto(List<Integer> generated) {
        this.generatedNumbers = new ArrayList<>(generated);
    }

    public int getNumberWithDigit(int digit) {
        return generatedNumbers.get(digit);
    }

    public boolean hasSameNumber(Bonus bonus) {
        // bonus의 getter 사용한 구간
        return generatedNumbers.contains(bonus.getBonusNumber());
    }
}
