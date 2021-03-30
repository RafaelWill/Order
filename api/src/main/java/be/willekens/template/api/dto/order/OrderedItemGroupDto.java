package be.willekens.template.api.dto.order;

public class OrderedItemGroupDto {

    private String itemId;
    private int amountOfItems;

    public OrderedItemGroupDto() {
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }
}
