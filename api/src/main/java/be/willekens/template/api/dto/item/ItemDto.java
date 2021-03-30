package be.willekens.template.api.dto.item;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ItemDto {

    private String id;
    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public ItemDto() {
    }

    public String getId() {
        return id;
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

    public ItemDto setId(String id) {
        this.id = id;
        return this;
    }

    public ItemDto setName(String name) {
        this.name = name;
        return this;
    }

    public ItemDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemDto setPrice(double price) {
        this.price = price;
        return this;
    }

    public ItemDto setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
        return this;
    }
}
