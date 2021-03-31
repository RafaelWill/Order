package be.willekens.template.domain.repository;

import be.willekens.template.domain.models.customer.Customer;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepository {

    private final Set<Customer> customers = new HashSet<>();

    public CustomerRepository() {
    }

    public Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public Collection<Customer> getAllCustomers() {
        return Collections.unmodifiableSet(customers);
    }

    public Optional<Customer> getCustomerById(String customerId) {
        return customers.stream().filter(customer -> customer.getId().toString().equals(customerId)).findFirst();
    }

}
