package be.willekens.template.infrastructure.exceptions;

public class NotAuthorizedException extends RuntimeException {

    public NotAuthorizedException() {
        super("You are not authorized to perform this action on this ID");
    }
}
