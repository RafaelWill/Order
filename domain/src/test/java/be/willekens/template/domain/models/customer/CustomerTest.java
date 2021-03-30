package be.willekens.template.domain.models.customer;

import be.willekens.template.infrastructure.exceptions.InvalidEmailException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void customerWithInvalidEmailShouldThrowException() {
        assertThrows(InvalidEmailException.class, () -> new Customer("test","test","test@test",
                new Address("test","test","test","test"),"test"));
    }

}