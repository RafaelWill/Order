package be.willekens.template.domain.repository;

import be.willekens.template.domain.models.Address;
import be.willekens.template.domain.models.Customer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {

    private final CustomerRepository customerRepository = new CustomerRepository();

    @Test
    void correctlyAddingOneCustomerToTheDatabase() {
        Customer customerOne = new Customer("test","test","test@test.be", new Address("test","test","test","test"),"test");
        customerRepository.addCustomer(customerOne);
        assertThat(1).isEqualTo(customerRepository.getAllCustomers().size());
    }

    @Test
    void correctlyAddingTwoCustomerWithDifferentEmailToTheDatabase() {
        Customer customerOne = new Customer("test","test","test@test.be", new Address("test","test","test","test"),"test");
        Customer customerTwo = new Customer("test","test","test@test.com", new Address("test","test","test","test"),"test");
        customerRepository.addCustomer(customerOne);
        customerRepository.addCustomer(customerTwo);
        assertThat(2).isEqualTo(customerRepository.getAllCustomers().size());
    }

    @Test
    void onlyAddFirstEntryIfAnotherCustomerWantsRegisterWithSameEmail() {
        Customer customerOne = new Customer("test","test","test@test.com", new Address("test","test","test","test"),"test");
        Customer customerTwo = new Customer("test","test","test@test.com", new Address("test","test","test","test"),"test");
        customerRepository.addCustomer(customerOne);
        customerRepository.addCustomer(customerTwo);
        assertThat(1).isEqualTo(customerRepository.getAllCustomers().size());
    }
}