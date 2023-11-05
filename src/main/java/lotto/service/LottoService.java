package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.LottoWithBonus;
import lotto.domain.UserMoney;
import lotto.repository.LottoRepository;
import lotto.util.LottoGenerator;

public class LottoService {
    public static final int SPECIAL_CASE = 5;
    public static final int SPECIAL_RESULT = 7;

    public static final int START_INCLUSIVE = 0;
    public static final int MIN_VALUE = 1;
    public static final int MAX_VALUE = 45;

    public static void generateRandomLotto(UserMoney money, LottoRepository repository) {
        int chances = money.getLottoChances();
        IntStream.range(START_INCLUSIVE, chances)
                .forEach((num) -> repository.saveLotto(
                        LottoGenerator.generateLotto(() -> Randoms.pickNumberInRange(MIN_VALUE, MAX_VALUE))
                ));
    }

    public static LottoResult compareLottoWithBonus(LottoWithBonus userLotto, Lotto generatedRandomLotto) {
        int sameNumberCount = compareEachLotto(userLotto, generatedRandomLotto);
        if ( sameNumberCount == SPECIAL_CASE && isBonusInLotto(userLotto, generatedRandomLotto)) {
            sameNumberCount = SPECIAL_RESULT;
        }
        return LottoResult.findLottoResultBySameNumberCount(sameNumberCount);
    }

    private static int compareEachLotto(LottoWithBonus userLotto, Lotto generatedRandomLotto) {
        return userLotto.findSameNumberCount(generatedRandomLotto);
    }

    private static boolean isBonusInLotto(LottoWithBonus userLottoWithBonus, Lotto generatedRandomLotto) {
        return userLottoWithBonus.isBonusInLotto(generatedRandomLotto);
    }

    // 결과를 출력하기 위한 DTO를 형성할 수 있어야 한다
}