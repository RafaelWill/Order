package be.willekens.template.api.dto.order;

import java.time.LocalDate;

public class ItemGroupDto {

    private String itemId;
    private int amountOfItems;
    private LocalDate shippingDate;
    private double totalPrice;

    public ItemGroupDto() {
    }

    public String getItemId() {
        return itemId;
    }

    public int getAmountOfItems() {
        return amountOfItems;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public ItemGroupDto setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemGroupDto setAmountOfItems(int amountOfItems) {
        this.amountOfItems = amountOfItems;
        return this;
    }

    public ItemGroupDto setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public ItemGroupDto setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
