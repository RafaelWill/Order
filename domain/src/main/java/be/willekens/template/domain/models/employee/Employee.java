package be.willekens.template.domain.models.employee;

import be.willekens.template.infrastructure.exceptions.InvalidEmailException;
import be.willekens.template.infrastructure.utils.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Employee {

    private static final Logger logger = LoggerFactory.getLogger(Employee.class);

    private final UUID id;
    private String name;
    private String email;
    private Role role;

    public Employee(String name, String email, Role role) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = emailValidator(email);
        this.role = role;
    }

    public Employee(UUID id, String name, String email, Role role) {
        this.id = id;
        this.name = name;
        this.email = emailValidator(email);
        this.role = role;
    }

    private String emailValidator(String email) {
        if (!EmailUtils.isValidEmail(email)) {
            logger.warn("The user tried to register an invalid e-mail");
            throw new InvalidEmailException("The e-mail: " + email + " is invalid");
        }
        return email;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
