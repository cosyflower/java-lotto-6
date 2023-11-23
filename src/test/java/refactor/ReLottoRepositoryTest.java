package refactor;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import refactor.lottoGenerator.GeneratedLotto;
import refactor.lottoGenerator.LottoRepository;
import refactor.lottoGenerator.RandomGenerator;

public class ReLottoRepositoryTest {
    private final LottoRepository lottoRepository = new LottoRepository();
    @Test
    void 생성된_로또_저장하기() {
        List<Integer> sortedLottoNumbers = RandomGenerator.generateSortedLotto();
        lottoRepository.addLotto(new GeneratedLotto(sortedLottoNumbers));

        Assertions.assertThat(lottoRepository.getGeneratedLottos().size()).isEqualTo(1);
    }
}
