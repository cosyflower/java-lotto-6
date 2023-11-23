package refactor.lottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGenerator {
    private static final Random random = new Random();
    private static final int LOTTO_SIZE = 6;

    public static List<Integer> generateSortedLotto() {
        List<Integer> lotto = new ArrayList<>();
        generateNumbers(lotto);
        Collections.sort(lotto);
        return lotto;
    }

    private static void generateNumbers(List<Integer> lotto) {
        while (hasUnvalidSize(lotto)) {
            int randomNumber = random.nextInt(45) + 1;
            if (lotto.contains(randomNumber)) {
                continue;
            }
            lotto.add(randomNumber);
        }
    }

    private static boolean hasUnvalidSize(List<Integer> lotto) {
        return lotto.size() != LOTTO_SIZE;
    }
}
