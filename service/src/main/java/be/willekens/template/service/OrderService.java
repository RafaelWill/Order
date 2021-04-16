package be.willekens.template.service;

import be.willekens.template.domain.models.order.ItemGroup;
import be.willekens.template.domain.models.order.Order;
import be.willekens.template.domain.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final ItemService itemService;

    public OrderService(OrderRepository orderRepository, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemService = itemService;
    }

    public Order addOrder(Order order) {
        validateItemIds(order);
        return orderRepository.addOrder(order);
    }

    public Collection<Order> getAllOrdersByCustomerId (String customerId) {
        return orderRepository.getAllOrdersByCustomerId(customerId);
    }

    public Order getOrderById(String orderId){
        return orderRepository.getOrderById(orderId).get();
    }

    private void validateItemIds(Order order) {
        order.getListOfOrderedItems().forEach(item -> itemService.getItemById(item.getItemCopy().getId().toString()));
    }

    public Collection<Order> getAllOrdersByShippingByDate(LocalDate localDate) {
        return orderRepository.getAllOrdersByShippingByDate(localDate);
    }

    public Order addReorder(String orderId) {
        return addOrder(createReorder(getOrderById(orderId)));
    }

    private Order createReorder(Order order) {
        return new Order(reorderItemGroups(order.getListOfOrderedItems()),order.getCustomerId());
    }

    private List<ItemGroup> reorderItemGroups(List<ItemGroup> listOfOrderedItems) {
        return listOfOrderedItems.stream().map(itemGroup -> new ItemGroup(itemService.getItemById(itemGroup.getItemCopy().getId().toString()) , itemGroup.getAmountOfItems())).collect(Collectors.toList());
    }

}
