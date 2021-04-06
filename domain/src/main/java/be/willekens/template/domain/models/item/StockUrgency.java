package be.willekens.template.domain.models.item;

public enum StockUrgency {
    STOCK_LOW("Low"),
    STOCK_MEDIUM("Medium"),
    STOCK_HIGH("High");

    private final String prettifiedName;

    StockUrgency(String prettifiedName) {
        this.prettifiedName = prettifiedName;
    }

    public String getPrettifiedName() {
        return prettifiedName;
    }

}
