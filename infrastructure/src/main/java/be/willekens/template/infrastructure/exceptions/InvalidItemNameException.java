package be.willekens.template.infrastructure.exceptions;

public class InvalidItemNameException extends RuntimeException {

    public InvalidItemNameException(String message) {
        super(message);
    }
}
