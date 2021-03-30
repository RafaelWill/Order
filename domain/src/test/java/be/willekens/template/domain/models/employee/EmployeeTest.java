package be.willekens.template.domain.models.employee;

import be.willekens.template.domain.models.customer.Address;
import be.willekens.template.domain.models.customer.Customer;
import be.willekens.template.infrastructure.exceptions.InvalidEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void employeeWithInvalidEmailShouldThrowException() {
        assertThrows(InvalidEmailException.class, () -> new Customer("test","test","test@test",
                new Address("test","test","test","test"),"test"));
    }

}