package be.willekens.template.domain.models.customer;

import be.willekens.template.infrastructure.exceptions.InvalidEmailException;
import be.willekens.template.infrastructure.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.UUID;

public class Customer {

    private static final Logger logger = LoggerFactory.getLogger(Customer.class);

    private final UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String phoneNumber;

    public Customer(String firstName, String lastName, String email, Address address, String phoneNumber) {
        id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = emailValidator(email);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private String emailValidator(String email) {
        if (!EmailUtils.isValidEmail(email)) {
            logger.warn("The user tried to register an invalid e-mail");
            throw new InvalidEmailException("The e-mail: " + email + " is invalid");
        }
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
