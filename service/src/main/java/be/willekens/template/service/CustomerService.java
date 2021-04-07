package be.willekens.template.service;

import be.willekens.template.domain.models.customer.Customer;
import be.willekens.template.domain.repository.CustomerRepository;
import be.willekens.template.infrastructure.exceptions.CustomerDoesNotExistException;
import be.willekens.template.infrastructure.exceptions.DuplicateEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer (Customer customer) {
        if (checkIfEmailExists(customer)) {
            logger.warn("A user tried to register an e-mail that already exists in our database");
            throw new DuplicateEmailException("The e-mail, " + customer.getEmail() + " already exists in our database");
        }
        return customerRepository.addCustomer(customer);
    }

    public Customer getCustomerById(String customerId) {
        if(!checkIfCustomerExists(customerId)) {
            logger.warn("A admin tried to view a customer with id " + customerId);
            throw new CustomerDoesNotExistException("This customer does not exist");
        }
        return customerRepository.getCustomerById(customerId).get();
    }

    public Collection<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public boolean checkIfCustomerExists(String customerId) {
        return customerRepository.getAllCustomers().stream().anyMatch(customer -> customer.getId().toString().equals(customerId));
    }

    private boolean checkIfEmailExists(Customer newCustomer) {
        return customerRepository.getAllCustomers().stream().anyMatch(customer -> customer.getEmail().equalsIgnoreCase(newCustomer.getEmail()));
    }

    public String getCustomerAddress(String customerId) {
        return customerRepository.getCustomerById(customerId).get().getAddress().toString();
    }
}
