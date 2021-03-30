package be.willekens.template.domain.models.order;

import be.willekens.template.domain.models.item.Item;

import java.time.LocalDate;

public class ItemGroup {

    private final Item item;
    private int amountOfItems;
    private LocalDate shippingDate;
    private double totalPrice;

    public ItemGroup(Item item, int amountOfItems) {
        this.item = item;
        this.amountOfItems = amountOfItems;
        this.shippingDate = determineShippingDate();
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return amountOfItems * item.getPrice();
    }

    private LocalDate determineShippingDate() {
        if (item.getAmountInStock() < amountOfItems) {
            return LocalDate.now().plusDays(7);
        }
        return LocalDate.now().plusDays(1);
    }

    public Item getItem() {
        return item;
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
}
