package be.willekens.template.infrastructure.exceptions;

public class InvalidStockValue extends RuntimeException {

    public InvalidStockValue(String message) {
        super(message);
    }
}
