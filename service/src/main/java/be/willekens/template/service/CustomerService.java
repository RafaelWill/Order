package be.willekens.template.service;

import be.willekens.template.domain.models.Customer;
import be.willekens.template.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer (Customer customer) {
        return customerRepository.addCustomer(customer);
    }
}
