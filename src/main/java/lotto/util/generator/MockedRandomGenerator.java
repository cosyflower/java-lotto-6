package lotto.util.generator;

import java.util.List;
import java.util.function.Supplier;

public class MockedRandomGenerator implements RandomStrategy {
    // 수정해야 함
    private final Supplier<List<Integer>> supplier;

    public MockedRandomGenerator(Supplier<List<Integer>> supplier) {
        this.supplier = supplier;
    }

    @Override
    public List<Integer> generate() {
        return supplier.get();
    }
}
