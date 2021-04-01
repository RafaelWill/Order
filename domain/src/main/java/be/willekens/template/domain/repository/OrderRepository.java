package be.willekens.template.domain.repository;

import be.willekens.template.domain.models.order.Order;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private final Set<Order> orders = new HashSet<>();

    public OrderRepository() {
    }

    public Order addOrder(Order order) {
        orders.add(order);
        return order;
    }

    public Collection<Order> getAllOrdersByCustomerId (String customerId) {
        return orders.stream().filter(customer -> customer.getCustomerId().equals(customerId)).collect(Collectors.toList());
    }

    public Optional<Order> getOrderById (String orderId) {
        return orders.stream().filter(order -> order.getId().toString().equals(orderId)).findFirst();
    }

    public boolean checkIfOrderExistsForThisCustomer(Collection<Order> ordersByCustomer, String orderId) {
        return ordersByCustomer.stream().anyMatch(order -> order.getId().toString().equals(orderId));
    }
}
