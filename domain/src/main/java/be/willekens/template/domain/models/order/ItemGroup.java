package be.willekens.template.domain.models.order;

import be.willekens.template.domain.models.item.Item;
import be.willekens.template.domain.models.item.ItemCopy;

import java.time.LocalDate;

public class ItemGroup {

    private final ItemCopy itemCopy;
    private final int amountOfItems;
    private final LocalDate shippingDate;
    private final double totalPrice;

    public ItemGroup(Item item, int amountOfItems) {
        this.itemCopy = new ItemCopy(item.getId(), item.getName(), item.getDescription(), item.getPrice());
        this.amountOfItems = amountOfItems;
        this.shippingDate = determineShippingDate(item.getAmountInStock(), amountOfItems);
        this.totalPrice = calculateTotalPrice();
        item.updateStockAmount(amountOfItems);
    }

    private double calculateTotalPrice() {
        return amountOfItems * itemCopy.getPrice();
    }

    private LocalDate determineShippingDate(int amountInStock, int amountOfItems) {
        if (amountInStock < amountOfItems) {
            return LocalDate.now().plusDays(7);
        }
        return LocalDate.now().plusDays(1);
    }

    public ItemCopy getItemCopy() {
        return itemCopy;
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
