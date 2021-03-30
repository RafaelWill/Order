package be.willekens.template.domain.repository;

import be.willekens.template.domain.models.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

}
