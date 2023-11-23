package refactor.lotteryResult;

import java.util.Arrays;
import java.util.EnumMap;

public class LottoReceipt {
    private final EnumMap<LotteryResult, Integer> receipt = new EnumMap(LotteryResult.class);

    public LottoReceipt() {
        Arrays.stream(LotteryResult.values())
                .forEach(lotteryResult -> receipt.put(lotteryResult, 0));
    }

    public void addEachLotteryResult(LotteryResult lotteryResult) {
        for (LotteryResult result : receipt.keySet()) {
            if (result == lotteryResult) {
                Integer value = receipt.get(result);
                receipt.replace(result, value + 1);
                break;
            }
        }
    }

    public int getEachLotteryResultCount(LotteryResult lotteryResult) {
        return receipt.get(lotteryResult);
    }

    public int getPrizeAmount() {
        return receipt.entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey().getLotteryAmount() * entry.getValue())
                .sum();
    }

    public EnumMap<LotteryResult, Integer> getReceipt() {
        return receipt;
    }
}
