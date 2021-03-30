package be.willekens.template.api.mappers;

import be.willekens.template.api.dto.CreateCustomerDto;
import be.willekens.template.api.dto.CustomerDto;
import be.willekens.template.domain.models.Address;
import be.willekens.template.domain.models.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Component
public class CustomerMapper {

    public CustomerMapper() {
    }

    public CustomerDto mapToCustomerDto(Customer customer) {
        return new CustomerDto()
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
