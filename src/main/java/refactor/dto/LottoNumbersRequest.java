package refactor.dto;

import refactor.userLotto.LottoNumbers;

public class LottoNumbersRequest {
    private final String inputLottoNumbers;

    public LottoNumbersRequest(String inputLottoNumbers) {
        validateInputLottoNumbers(inputLottoNumbers);
        this.inputLottoNumbers = inputLottoNumbers;
    }

    private void validateInputLottoNumbers(String inputLottoNumbers) {
        isNullOrEmpty(inputLottoNumbers);
        isLottoPattern(inputLottoNumbers);
    }

    private void isNullOrEmpty(String inputLottoNumbers) {
        if (inputLottoNumbers == null || inputLottoNumbers.isEmpty()) {
            throw new IllegalArgumentException("아무것도 입력하지 않았습니다.");
        }
    }

    private void isLottoPattern(String inputLottoNumbers) {
        if (RequestRegex.LOTTO_PATTERN.matcher(inputLottoNumbers).matches()) {
            return;
        }
        throw new IllegalArgumentException("로또 패턴이 아닙니다.");
    }

    public LottoNumbers toLottoNumbers() {
        return new LottoNumbers(Parser.splitInputBySeparator(inputLottoNumbers, ","));
    }
}
