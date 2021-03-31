package be.willekens.template.api.dto.order;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class OrdersByCustomerDto {

    private Collection<OrderDto> allOrders;
    private double totalPriceOfAllOrders;

    public OrdersByCustomerDto() {
    }

    private double calculateTotalPriceOfAllOrders() {
        return allOrders.stream().mapToDouble(OrderDto::getTotalPriceOfTheOrder).sum();
    }

    public OrdersByCustomerDto setAllOrders(Collection<OrderDto> allOrders) {
        this.allOrders = allOrders;
        return this;
    }

    public OrdersByCustomerDto setTotalPriceOfAllOrders() {
        this.totalPriceOfAllOrders = calculateTotalPriceOfAllOrders();
        return this;
    }
}
