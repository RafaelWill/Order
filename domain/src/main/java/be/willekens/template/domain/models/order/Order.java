package be.willekens.template.domain.models.order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Order {


    private final UUID id;
    private final String customerId;
    private final List<ItemGroup> listOfOrderedItems = new LinkedList<>();
    private double totalPrice;

    public Order(List<ItemGroup> listOfOrderedItems, String customerId) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        addItemGroup(listOfOrderedItems);
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        return listOfOrderedItems.stream().mapToDouble(ItemGroup::getTotalPrice).sum();
    }

    public void addItemGroup(List<ItemGroup> orderedItems) {
        listOfOrderedItems.addAll(orderedItems);
    }

    public UUID getId() {
        return id;
    }

    public Collection<ItemGroup> getListOfOrderedItems() {
        return Collections.unmodifiableList(listOfOrderedItems);
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
