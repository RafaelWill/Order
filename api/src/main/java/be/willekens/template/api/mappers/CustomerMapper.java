package be.willekens.template.api.mappers;

import be.willekens.template.api.dto.customer.CreateCustomerDto;
import be.willekens.template.api.dto.customer.CustomerDto;
import be.willekens.template.domain.models.customer.Address;
import be.willekens.template.domain.models.customer.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Component
public class CustomerMapper {

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

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        return new Customer(createCustomerDto.getFirstName(), createCustomerDto.getLastName(), createCustomerDto.getEmail(),
                new Address(createCustomerDto.getStreetName(), createCustomerDto.getHouseNumber(), createCustomerDto.getPostalCode(), createCustomerDto.getCity()),
                createCustomerDto.getPhoneNumber());
    }
}
