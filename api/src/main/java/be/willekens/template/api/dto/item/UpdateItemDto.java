package be.willekens.template.api.dto.item;

public class UpdateItemDto {

    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public UpdateItemDto() {
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
