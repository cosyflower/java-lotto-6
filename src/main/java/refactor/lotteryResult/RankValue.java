package refactor.lotteryResult;

public enum RankValue {
    SIX(6),
    FIVE(5),
    FOUR(4),
    THREE(3)
    ;

    private final int sameNumberCount;

    RankValue(int rankValue) {
        this.sameNumberCount = rankValue;
    }

    public int getSameNumberCount() {
        return sameNumberCount;
    }
}
