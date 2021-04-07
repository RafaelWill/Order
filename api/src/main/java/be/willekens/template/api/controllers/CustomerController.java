package be.willekens.template.api.controllers;

import be.willekens.template.api.dto.customer.CreateCustomerDto;
import be.willekens.template.api.dto.customer.CustomerDto;
import be.willekens.template.api.mappers.CustomerMapper;
import be.willekens.template.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @PostMapping(path = "/signup", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto createACustomer (@RequestBody CreateCustomerDto createCustomerDto) {
        logger.info("A user is requesting to create a new member account");
        return  customerMapper.mapToCustomerDto(customerService.addCustomer(customerMapper.createCustomer(createCustomerDto)));
    }

    @PreAuthorize("hasAuthority('GET_ALL_CUSTOMERS')")
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Collection<CustomerDto> getAllCustomers() {
        logger.info("An admin is requesting to view all customers");
        return customerMapper.mapListToCustomerDto(customerService.getAllCustomers());
    }

    @PreAuthorize("hasAuthority('GET_CUSTOMER_BY_ID')")
    @GetMapping(path = "/{customerId}" ,produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable String customerId) {
        logger.info("An admin is requesting to view a specific customer with id " + customerId);
        return customerMapper.mapToCustomerDto(customerService.getCustomerById(customerId));
    }
}
