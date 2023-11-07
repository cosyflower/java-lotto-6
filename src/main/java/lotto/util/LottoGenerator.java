package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;
import lotto.service.LottoService;
import lotto.util.generator.RandomStrategy;

public class LottoGenerator {
    public static Lotto generateLotto(RandomStrategy randomGenerator) {
        List<Integer> randomNumbers = randomGenerator.generate();
        List<Integer> sortingNumbers = new ArrayList<>(randomNumbers);
        Collections.sort(sortingNumbers);
        return Lotto.from(sortingNumbers);
    }
}
