package be.willekens.template.api.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrderDto {

    private String orderId;
    private String customerId;
    private List<ItemGroupDto> orderedItems;
    private double totalPriceOfTheOrder;

    public OrderDto() {
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Collection<ItemGroupDto> getOrderedItems() {
        return orderedItems;
    }

    public double getTotalPriceOfTheOrder() {
        return totalPriceOfTheOrder;
    }

    public OrderDto setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderDto setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderDto setOrderedItems(List<ItemGroupDto> orderedItems) {
        this.orderedItems = orderedItems;
        return this;
    }

    public OrderDto setTotalPriceOfTheOrder(double totalPriceOfTheOrder) {
        this.totalPriceOfTheOrder = totalPriceOfTheOrder;
        return this;
    }

}
