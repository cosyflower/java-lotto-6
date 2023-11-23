package refactor.lottoGenerator;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReRandomGeneratorTest {
    @Test
    void flow() {
        List<Integer> integers = RandomGenerator.generateSortedLotto();
        for (Integer integer : integers) {
            Assertions.assertThat(integer).isBetween(1, 45);
        }
    }
}
