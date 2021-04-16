package be.willekens.template;

import be.willekens.template.api.controllers.CustomerController;
import be.willekens.template.api.dto.customer.CreateCustomerDto;
import be.willekens.template.api.dto.customer.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIntegrationTest {

    @Autowired
    private CustomerController customerController;

    @Test
    @WithMockUser(authorities = {"GET_ALL_CUSTOMERS"})
    void getAllCustomers_testingTheDifferentLayersAccess_withoutDto() {
        Collection<CustomerDto> customers = customerController.getAllCustomers();

        customers.forEach(System.out::println);
    }

    @Test
    @WithMockUser(authorities = {"GET_ALL_CUSTOMERS"})
    void getAllCustomers_testingTheDifferentLayersAccess_withDto() {
        customerController.createACustomer(
                new CreateCustomerDto("test","test","Testy","Testion","test@test.be",
                        "Teststreet","5","3800","Test Town","123456789"));

        Collection<CustomerDto> customers = customerController.getAllCustomers();

        customers.forEach(System.out::println);
    }
}