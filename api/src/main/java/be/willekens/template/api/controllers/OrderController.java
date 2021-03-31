package be.willekens.template.api.controllers;

import be.willekens.template.api.dto.order.OrderDto;
import be.willekens.template.api.dto.order.SubmittedOrderDto;
import be.willekens.template.api.mappers.OrderMapper;
import be.willekens.template.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto placeAnOrder (@RequestHeader String customerId, @RequestBody SubmittedOrderDto submittedOrderDto ) {
        logger.info("A user is requesting to create a new order");
        return orderMapper.mapToDto(orderService.addOrder(orderMapper.createOrder(submittedOrderDto, customerId),customerId));
    }

}
