package be.willekens.template.api.mappers;

import be.willekens.template.api.dto.customer.CreateCustomerDto;
import be.willekens.template.api.dto.customer.CustomerDto;
import be.willekens.template.domain.models.customer.Address;
import be.willekens.template.domain.models.customer.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Component
public class CustomerMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public CustomerDto mapToCustomerDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
//        return new CustomerDto()
//                .setId(customer.getId().toString())
//                .setFirstName(customer.getFirstName())
//                .setLastName(customer.getLastName())
//                .setEmail(customer.getEmail())
//                .setStreetName(customer.getAddress().getStreetName())
//                .setHouseNumber(customer.getAddress().getHouseNumber())
//                .setPostalCode(customer.getAddress().getPostalCode())
//                .setCity(customer.getAddress().getCity())
//                .setPhoneNumber(customer.getPhoneNumber());
    }

    public Customer createCustomer(CreateCustomerDto createCustomerDto) {
        return new Customer(createCustomerDto.getFirstName(), createCustomerDto.getLastName(), createCustomerDto.getEmail(),
                new Address(createCustomerDto.getStreetName(), createCustomerDto.getHouseNumber(), createCustomerDto.getPostalCode(), createCustomerDto.getCity()),
                createCustomerDto.getPhoneNumber());
    }

    public Collection<CustomerDto> mapListToCustomerDto(Collection<Customer> allCustomers) {
        return allCustomers.stream().map(this::mapToCustomerDto).collect(Collectors.toList());
    }
}
