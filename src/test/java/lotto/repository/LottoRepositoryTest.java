package lotto.repository;

import java.util.Arrays;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoGame;
import lotto.generator.MockedRandomGenerator;
import lotto.repository.LottoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

public class LottoRepositoryTest {
    private LottoGame lottoGame = new LottoGame();
    private LottoRepository lottoRepository = new LottoRepository();

    @Test
    void 로도_생성_이후_저장하기() {
        Lotto lotto = lottoGame.generateLotto(new MockedRandomGenerator());

        lottoRepository.saveLotto(lotto);

        Assertions.assertThat(lottoRepository.checkRepositorySize()).isEqualTo(1);
    }


    private static Stream<Arguments> generateLotto() {
        return Stream.of(
                Arguments.of(Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6))),
                Arguments.of(Lotto.from(Arrays.asList(7, 8, 9, 10, 11, 12))),
                Arguments.of(Lotto.from(Arrays.asList(13, 14, 15, 16, 17, 18)))
        );
    }

    @Test
    void 로또_번호_조회하기() {
        Lotto firstLotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto secondLotto = Lotto.from(Arrays.asList(7, 8, 9, 10, 11, 12));
        Lotto thirdLotto = Lotto.from(Arrays.asList(13, 14, 15, 16, 17, 18));

        lottoRepository.saveLotto(firstLotto);
        lottoRepository.saveLotto(secondLotto);
        lottoRepository.saveLotto(thirdLotto);

        Assertions.assertThat(lottoRepository.checkRepositorySize()).isEqualTo(3);
        Assertions.assertThat(lottoRepository.findLottoNumbersByIndex(0))
                .containsExactly(1,2,3,4,5,6);
        Assertions.assertThat(lottoRepository.findLottoNumbersByIndex(1))
                .containsExactly(7, 8, 9, 10, 11, 12);
        Assertions.assertThat(lottoRepository.findLottoNumbersByIndex(2))
                .containsExactly(13, 14, 15, 16, 17, 18);
    }

}