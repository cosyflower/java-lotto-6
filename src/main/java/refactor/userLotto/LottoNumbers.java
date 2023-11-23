package refactor.userLotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import refactor.lottoGenerator.GeneratedLotto;

public class LottoNumbers {
    private static final int START_INCLUSIVE = 0;
    private final List<Integer> lottoNumbers;

    public LottoNumbers(List<Integer> numbers) {
        validateLottoNumbers(numbers);
        lottoNumbers = new ArrayList<>(numbers);
    }

    private void validateLottoNumbers(List<Integer> lottoNumbers) {;
        isValidSize(lottoNumbers);
        isValidRange(lottoNumbers);
        hasDuplicatedLottoNumber(lottoNumbers);
    }

    private void isValidSize(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() == 6) {
            return;
        }
        throw new IllegalArgumentException("로또 번호의 개수가 올바르지 않습니다.");
    }

    private void isValidRange(List<Integer> lottoNumbers) {
        if (lottoNumbers.stream()
                .anyMatch(number -> isNotInRange(number))) {
            throw new IllegalArgumentException("로또 번호가 범위에 맞지 않습니다.");
        }
    }

    private boolean isNotInRange(Integer number) {
        return number < 1 || number > 45;
    }

    private void hasDuplicatedLottoNumber(List<Integer> lottoNumbers) {
        if (lottoNumbers.size() == lottoNumbers.stream().distinct().count()) {
            return;
        }
        throw new IllegalArgumentException("중복된 로또 번호입니다.");
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
