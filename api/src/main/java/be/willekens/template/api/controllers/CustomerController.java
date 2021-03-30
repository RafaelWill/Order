package be.willekens.template.api.controllers;

import be.willekens.template.api.dto.CreateCustomerDto;
import be.willekens.template.api.dto.CustomerDto;
import be.willekens.template.api.mappers.CustomerMapper;
import be.willekens.template.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createACustomer (@RequestBody CreateCustomerDto createCustomerDto) {
        logger.info("A user is requesting to create a new member account");
        return  customerMapper.mapToCustomerDto(customerService.addCustomer(customerMapper.createCustomer(createCustomerDto)));
    }
}
