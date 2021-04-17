package be.willekens.template.api.controllers;

import be.willekens.template.api.dto.order.*;
import be.willekens.template.api.mappers.OrderMapper;
import be.willekens.template.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PreAuthorize("hasAuthority('PLACE_ORDER') and @securityService.canAccessId(authentication, #submittedOrderDto.customerId)")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto placeAnOrder (@RequestBody SubmittedOrderDto submittedOrderDto, Authentication authentication) {
        logger.info("A customer with id " + submittedOrderDto.getCustomerId() + " is requesting to create a new order");
        return orderMapper.mapToDto(orderService.addOrder(orderMapper.createOrder(submittedOrderDto)));
    }

    @PreAuthorize("hasAuthority('GET_HISTORY_OF_ORDERS') and @securityService.canAccessId(authentication, #customerId)")
    @GetMapping(path = "/history", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrdersByCustomerDto getAllOrdersByCustomerId (@RequestParam String customerId, Authentication authentication) {
        logger.info("A customer with id " + customerId +  " is requesting to view his history of orders");
        return orderMapper.mapToOrdersByCustomer(orderService.getAllOrdersByCustomerId(customerId));
    }

    @PreAuthorize("hasAuthority('REORDER') and @securityService.canAccessOrderId(authentication, #orderId)")
    @PostMapping(path = "/{orderId}/reorder", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reorderExistingOrder(@PathVariable String orderId, Authentication authentication) {
        logger.info("A customer is requesting to reorder the order with id " + orderId);
        return orderMapper.mapToDto(orderService.addReorder(orderId));
    }

    @PreAuthorize("hasAuthority('GET_ORDERS_BY_SHIPPING_DATE')")
    @GetMapping(path = "/shipping", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ShippingDto getOrdersToBeShippedToday(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate ) {
        logger.info("An admin is requesting a list of all orders that needs to be shipped on the provided date");
        return orderMapper.mapToShippingDto(orderService.getAllOrdersByShippingByDate(localDate));
    }

}
