package be.willekens.template.infrastructure.exceptions;

public class InvalidPriceException extends RuntimeException {

    public InvalidPriceException(String message) {
        super(message);
    }
}
