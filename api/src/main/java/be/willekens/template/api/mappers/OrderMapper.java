package be.willekens.template.api.mappers;

import be.willekens.template.api.dto.order.*;
import be.willekens.template.domain.models.order.ItemGroup;
import be.willekens.template.domain.models.order.Order;
import be.willekens.template.service.CustomerService;
import be.willekens.template.service.ItemService;
import be.willekens.template.service.OrderService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ItemService itemService;
    private final CustomerService customerService;

    public OrderMapper(ItemService itemService, CustomerService customerService) {
        this.itemService = itemService;
        this.customerService = customerService;
    }

    /*--- Creating OrderDto & ItemGroupDto ---*/
    public OrdersByCustomerDto mapToOrdersByCustomer(Collection<Order> allOrdersByCustomerId) {
        return new OrdersByCustomerDto()
                .setAllOrders(allOrdersByCustomerId.stream().map(this::mapToDto).collect(Collectors.toList()))
                .setTotalPriceOfAllOrders();
    }

    public OrderDto mapToDto(Order addOrder) {
        return new OrderDto()
                .setOrderId(addOrder.getId().toString())
                .setCustomerId(addOrder.getCustomerId())
                .setOrderedItems(mapListToItemGroupDto(addOrder.getListOfOrderedItems()))
                .setTotalPriceOfTheOrder(addOrder.getTotalPrice());
    }

    private List<ItemGroupDto> mapListToItemGroupDto(Collection<ItemGroup> listOfOrderedItems) {
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

    /*--- Creating Order & ItemGroup ---*/
    public Order createOrder(SubmittedOrderDto submittedOrderDto) {
        return new Order(submittedOrderDto.getOrderedItems().stream().map(this::createItemGroup).collect(Collectors.toList()), submittedOrderDto.getCustomerId());
    }

    private ItemGroup createItemGroup(OrderedItemGroupDto orderedItemGroupDto) {
        return new ItemGroup(itemService.getItemById(orderedItemGroupDto.getItemId()), orderedItemGroupDto.getAmountOfItems());
    }

    /*--- Creating ShippingDto & ItemToShipDto ---*/
    public ShippingDto mapToShippingDto(Collection<Order> allOrdersByShippingDateToday) {
        return new ShippingDto().setAllOrders(mapListToItemToShipDto(allOrdersByShippingDateToday));
    }

    private List<ItemToShipDto> mapListToItemToShipDto(Collection<Order> allOrdersByShippingDateToday) {
        List<ItemToShipDto> listOfItemsToShip = new LinkedList<>();

        for (Order order: allOrdersByShippingDateToday) {
            for (ItemGroup itemGroup : order.getListOfOrderedItems()) {
                if (itemGroup.getShippingDate().equals(LocalDate.now().plusDays(1))) {
                    listOfItemsToShip.add(new ItemToShipDto()
                            .setItemId(itemGroup.getItemCopy().getId().toString())
                            .setItemName(itemGroup.getItemCopy().getName())
                            .setAddress(customerService.getCustomerAddress(order.getCustomerId())));
                }
            }
        }
        return listOfItemsToShip;
    }

}
