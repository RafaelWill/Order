package be.willekens.template.api.mappers;

import be.willekens.template.api.dto.order.*;
import be.willekens.template.domain.models.order.ItemGroup;
import be.willekens.template.domain.models.order.Order;
import be.willekens.template.service.ItemService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ItemService itemService;

    public OrderMapper(ItemService itemService) {
        this.itemService = itemService;
    }

    public Order createOrder(SubmittedOrderDto submittedOrderDto, String customerId) {
        return new Order(submittedOrderDto.getOrderedItems().stream().map(this::createItemGroup).collect(Collectors.toList()), customerId);
    }

    public OrderDto mapToDto(Order addOrder) {
        return new OrderDto()
                .setOrderId(addOrder.getId().toString())
                .setCustomerId(addOrder.getCustomerId())
                .setOrderedItems(mapListToItemGroupDto(addOrder.getListOfOrderedItems()))
                .setTotalPriceOfTheOrder(addOrder.getTotalPrice());
    }

    public OrdersByCustomerDto mapToOrdersByCustomer(Collection<Order> allOrdersByCustomerId) {
        return new OrdersByCustomerDto()
                .setAllOrders(allOrdersByCustomerId.stream().map(this::mapToDto).collect(Collectors.toList()))
                .setTotalPriceOfAllOrders();
    }

    private ItemGroup createItemGroup(OrderedItemGroupDto orderedItemGroupDto) {
        return new ItemGroup(itemService.getItemById(orderedItemGroupDto.getItemId()), orderedItemGroupDto.getAmountOfItems());
    }

    private Collection<ItemGroupDto> mapListToItemGroupDto(Collection<ItemGroup> listOfOrderedItems) {
        return listOfOrderedItems.stream().map(this::mapToItemGroupDto).collect(Collectors.toList());
    }

    private ItemGroupDto mapToItemGroupDto(ItemGroup itemGroup) {
        return new ItemGroupDto()
                .setItemId(itemGroup.getItemCopy().getId().toString())
                .setItemName(itemGroup.getItemCopy().getName())
                .setAmountOfItems(itemGroup.getAmountOfItems())
                .setShippingDate(itemGroup.getShippingDate())
                .setTotalPrice(itemGroup.getTotalPrice());
    }

}
