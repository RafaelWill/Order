package be.willekens.template.infrastructure.exceptions;

public class OrderDoesNotExistException extends RuntimeException {

    public OrderDoesNotExistException(String message) {
        super(message);
    }
}
