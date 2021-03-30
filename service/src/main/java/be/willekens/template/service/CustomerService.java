package be.willekens.template.service;

import be.willekens.template.domain.models.Customer;
import be.willekens.template.domain.repository.CustomerRepository;
import be.willekens.template.infrastructure.exceptions.DuplicateEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer (Customer customer) {
        if (!checkIfEmailExists(customer)) {
            logger.warn("A user tried to register an e-mail that already exists in our database");
            throw new DuplicateEmailException("This e-mail already exists in our database");
        }
        return customerRepository.addCustomer(customer);
    }

    private boolean checkIfEmailExists(Customer newCustomer) {
        return customerRepository.getAllCustomers().stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(newCustomer.getEmail()))
                .collect(Collectors.toList())
                .isEmpty();
    }
}
