package be.willekens.template.infrastructure.exceptions;

public class CustomerDoesNotExistException extends RuntimeException {

    public CustomerDoesNotExistException(String message) {
        super(message);
    }
}
