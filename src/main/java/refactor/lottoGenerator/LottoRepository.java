package refactor.lottoGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoRepository {
    private final List<GeneratedLotto> generatedLottos;

    public LottoRepository() {
        this.generatedLottos = new ArrayList<>();
    }

    public void addLotto(GeneratedLotto generatedLotto) {
        generatedLottos.add(generatedLotto);
    }

    public List<GeneratedLotto> getGeneratedLottos() {
        return Collections.unmodifiableList(generatedLottos);
    }
}
