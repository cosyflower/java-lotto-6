package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoFinalResult;
import lotto.domain.UserMoney;
import lotto.dto.input.UserBonusDTO;
import lotto.dto.input.UserLottoDTO;
import lotto.dto.input.UserMoneyDTO;
import lotto.io.Reader;
import lotto.io.Writer;
import lotto.repository.LottoRepository;

public class LottoScreen {
    public static final String RATE_FORMAT = "%.1f";
    public static final String RESULT_PREFIX = "총 수익률은";
    public static final String RESULT_SUFFIX = "입니다.\n";
    public static final String RESULT_FORMAT = RESULT_PREFIX + RATE_FORMAT +RESULT_SUFFIX;
    public static final String REWIND_FORMAT = "%d개를 구매했습니다.\n";

    public static final String LOTTO_RESULT_FORMAT = "%s (%d원) - %d개\n";
    private static StringBuilder stringbuilder = new StringBuilder();

    private final Reader reader;
    private final Writer writer;

    public LottoScreen(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public UserBonusDTO registerBonus() {
        writer.writeLine("보너스 번호를 입력해 주세요");
        return new UserBonusDTO(reader.readLine().trim());
    }

    public UserMoneyDTO inputUserMoney() {
        writer.writeLine("구입 금액을 입력해 주세요");
        return new UserMoneyDTO(reader.readLine().trim());
    }

    public UserLottoDTO registerLotto() {
        writer.writeLine("당첨 번호를 입력해 주세요");
        return new UserLottoDTO(reader.readLine().trim());
    }

    public void displayGeneratedLotto(UserMoney userMoney, LottoRepository lottoRepository) {
        writer.writeFormat(REWIND_FORMAT, userMoney.getLottoChances());

        List<Lotto> allLottos = lottoRepository.getAllLottos();
        List<String> convertedLottos = allLottos.stream()
                .map((lotto) -> String.join(", ", convertNumbers(lotto.getLotto())))
                .toList();

        convertedLottos.forEach((lotto -> writer.writeFormat("[%s]\n", lotto)));
    }

    public void displayLottoResult(LottoFinalResult lottoFinalResult) {
        writer.writeLine("당첨 통계");
        writer.writeLine("---");
        lottoFinalResult.getFinalResultMap()
                .entrySet()
                .forEach((set) -> {
                    writer.writeFormat(LOTTO_RESULT_FORMAT, set.getKey().getDescription(),
                            set.getKey().getWinningAmount(), set.getValue());
                });
    }

    public void displayRateOfReturn(float rateOfReturn) {
        writer.writeFormat(RESULT_FORMAT, RESULT_PREFIX, rateOfReturn, RESULT_SUFFIX);
    }

    private List<String> convertNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map((number) -> number.toString())
                .toList();
    }
}
