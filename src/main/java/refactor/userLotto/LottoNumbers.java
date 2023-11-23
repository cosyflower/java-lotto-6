package refactor.userLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import refactor.GeneratedLotto;

public class LottoNumbers {
    private static final int START_INCLUSIVE = 0;
    private final List<Integer> lottoNumbers;

    public LottoNumbers(List<Integer> numbers) {
        lottoNumbers = new ArrayList<>(numbers);
    }

    public int compareNumberWithDigit(GeneratedLotto generatedLotto) {
        int compareCount = lottoNumbers.size();
        long count = IntStream.range(START_INCLUSIVE, compareCount)
                .filter(num -> hasSameNumberWithDigit(generatedLotto, num))
                .count();
        return (int) count;
    }

    private boolean hasSameNumberWithDigit(GeneratedLotto generatedLotto, int digit) {
        int digitNumber = generatedLotto.getNumberWithDigit(digit);
        return digitNumber == lottoNumbers.get(digit);
    }
}
