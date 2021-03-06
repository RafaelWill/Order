package be.willekens.template.api.dto.item;

public class CreateItemDto {

    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public CreateItemDto() {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }
}
