package be.willekens.template.api.mappers;

import be.willekens.template.api.dto.customer.CreateCustomerDto;
import be.willekens.template.api.dto.customer.CustomerDto;
import be.willekens.template.domain.models.customer.Address;
import be.willekens.template.domain.models.customer.Customer;
import be.willekens.template.security.users.SecurityService;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Component
public class CustomerMapper {

    private final SecurityService securityService;

    public CustomerMapper(SecurityService securityService) {
        this.securityService = securityService;
    }

    /*--- Creating CustomerDto ---*/
    public CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto()
                .setId(customer.getId().toString())
                .setFirstName(customer.getFirstName())
                .setLastName(customer.getLastName())
                .setEmail(customer.getEmail())
                .setStreetName(customer.getAddress().getStreetName())
                .setHouseNumber(customer.getAddress().getHouseNumber())
                .setPostalCode(customer.getAddress().getPostalCode())
                .setCity(customer.getAddress().getCity())
                .setPhoneNumber(customer.getPhoneNumber());
    }

    public Collection<CustomerDto> mapListToCustomerDto(Collection<Customer> allCustomers) {
        return allCustomers.stream().map(this::mapToCustomerDto).collect(Collectors.toList());
    }

    /*--- Creating Customer ---*/
    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        securityService.addUser(createCustomerDto.getUsername(), createCustomerDto.getPassword());

        Customer customer = new Customer(createCustomerDto.getFirstName(), createCustomerDto.getLastName(), createCustomerDto.getEmail(),
                new Address(createCustomerDto.getStreetName(), createCustomerDto.getHouseNumber(), createCustomerDto.getPostalCode(), createCustomerDto.getCity()),
                createCustomerDto.getPhoneNumber());

        securityService.addUserAndCustomerId(createCustomerDto.getUsername(), customer.getId().toString());

        return customer;
    }
}
