package be.willekens.template.infrastructure.exceptions;

public class DuplicateItemNameException extends  RuntimeException{

    public DuplicateItemNameException(String message) {
        super(message);
    }
}
