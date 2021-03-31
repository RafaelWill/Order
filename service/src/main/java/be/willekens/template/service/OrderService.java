package be.willekens.template.service;

import be.willekens.template.domain.models.order.Order;
import be.willekens.template.domain.repository.OrderRepository;
import be.willekens.template.infrastructure.exceptions.CustomerDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ItemService itemService;

    public OrderService(OrderRepository orderRepository, CustomerService customerService, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.itemService = itemService;
    }

    public Order addOrder(Order order, String customerId) {
        if (!isKnownCustomer(customerId)) {
            logger.warn("A customer with id " + customerId + " tried to place an order");
            throw new CustomerDoesNotExistException("You can only place orders if you are a registered customer");
        }
        validateItemIds(order);
        return orderRepository.addOrder(order);
    }

    public Collection<Order> getAllOrdersByCustomerId (String customerId) {
        if (!isKnownCustomer(customerId)) {
            logger.warn("A customer with id " + customerId + " tried view his order history");
            throw new CustomerDoesNotExistException("You can only view your order history if you are a registered customer");
        }
        return orderRepository.getAllOrdersByCustomerId(customerId);
    }

    private boolean isKnownCustomer(String customerId) {
        return customerService.checkIfCustomerExists(customerId);
    }

    private void validateItemIds(Order order) {
        order.getListOfOrderedItems().forEach(item -> itemService.getItemById(item.getItemCopy().getId().toString()));
    }

}
