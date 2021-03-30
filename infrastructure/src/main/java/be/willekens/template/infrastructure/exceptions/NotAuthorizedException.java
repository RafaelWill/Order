package be.willekens.template.infrastructure.exceptions;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException(String message) {
        super(message);
    }
}
