package be.willekens.template.api.controllers;

import be.willekens.template.api.dto.order.*;
import be.willekens.template.api.mappers.OrderMapper;
import be.willekens.template.infrastructure.exceptions.NotAuthorizedException;
import be.willekens.template.security.users.SecurityService;
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
@RequestMapping(path = "/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final SecurityService securityService;

    public OrderController(OrderService orderService, OrderMapper orderMapper, SecurityService securityService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.securityService = securityService;
    }

    @PreAuthorize("hasAuthority('PLACE_ORDER')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto placeAnOrder (@RequestBody SubmittedOrderDto submittedOrderDto, Authentication authentication) {
        logger.info("A customer with id " + submittedOrderDto.getCustomerId() + " is requesting to create a new order");
        if (!securityService.canAccessId(authentication,submittedOrderDto.getCustomerId())) {
            throw new NotAuthorizedException();
        }
        return orderMapper.mapToDto(orderService.addOrder(orderMapper.createOrder(submittedOrderDto)));
    }

    @PreAuthorize("hasAuthority('GET_HISTORY_OF_ORDERS')")
    @GetMapping(path = "/history", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public OrdersByCustomerDto getAllOrdersByCustomerId (@RequestParam String customerId, Authentication authentication) {
        logger.info("A customer with id " + customerId +  " is requesting to view his history of orders");
        if (!securityService.canAccessId(authentication, customerId)) {
            throw new NotAuthorizedException();
        }
        return orderMapper.mapToOrdersByCustomer(orderService.getAllOrdersByCustomerId(customerId));
    }

    @PreAuthorize("hasAuthority('REORDER')")
    @PostMapping(path = "/{orderId}/reorder", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reorderExistingOrder(@PathVariable String orderId, Authentication authentication) {
        logger.info("A customer is requesting to reorder the order with id " + orderId);
        if (!securityService.canAccessOrderId(authentication, orderId)){
            throw new NotAuthorizedException();
        }
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
