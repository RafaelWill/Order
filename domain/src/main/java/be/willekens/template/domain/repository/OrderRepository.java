package be.willekens.template.domain.repository;

import be.willekens.template.domain.models.order.Order;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class OrderRepository {

    private final Set<Order> orders = new HashSet<>();

    public OrderRepository() {
    }

    public Order addOrder(Order order) {
        orders.add(order);
        return order;
    }
}
