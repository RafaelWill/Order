package be.willekens.template.api.mappers;

import be.willekens.template.api.dto.order.ItemGroupDto;
import be.willekens.template.api.dto.order.OrderDto;
import be.willekens.template.api.dto.order.OrderedItemGroupDto;
import be.willekens.template.api.dto.order.SubmittedOrderDto;
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

    public Order createOrder(SubmittedOrderDto submittedOrderDto) {
        return new Order(submittedOrderDto.getOrderedItems().stream().map(this::createItemGroup).collect(Collectors.toList()), submittedOrderDto.getCustomerId());
    }

    private ItemGroup createItemGroup(OrderedItemGroupDto orderedItemGroupDto) {
        return new ItemGroup(itemService.getItemById(orderedItemGroupDto.getItemId()), orderedItemGroupDto.getAmountOfItems());
    }

    public OrderDto mapToDto(Order addOrder) {
        return new OrderDto()
                .setOrderId(addOrder.getId().toString())
                .setCustomerId(addOrder.getCustomerId())
                .setOrderedItems(mapListToItemGroupDto(addOrder.getListOfOrderedItems()))
                .setTotalPriceOfTheOrder(addOrder.getTotalPrice());
    }

    private Collection<ItemGroupDto> mapListToItemGroupDto(Collection<ItemGroup> listOfOrderedItems) {
        return listOfOrderedItems.stream().map(this::mapToItemGroupDto).collect(Collectors.toList());
    }

    private ItemGroupDto mapToItemGroupDto(ItemGroup itemGroup) {
        return new ItemGroupDto()
                .setItemId(itemGroup.getItem().getId().toString())
                .setAmountOfItems(itemGroup.getAmountOfItems())
                .setShippingDate(itemGroup.getShippingDate())
                .setTotalPrice(itemGroup.getTotalPrice());
    }
}
