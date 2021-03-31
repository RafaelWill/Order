package be.willekens.template.domain.models.item;



import be.willekens.template.infrastructure.exceptions.InvalidItemNameException;
import be.willekens.template.infrastructure.exceptions.InvalidPriceException;
import be.willekens.template.infrastructure.exceptions.InvalidStockValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;


public class Item {
    
    private static final Logger logger = LoggerFactory.getLogger(Item.class);

    private final UUID id;
    private String name;
    private String description;
    private double price;
    private int amountInStock;

    public Item(String name, String description, double price, int amountInStock) {
        this.id = UUID.randomUUID();
        this.name = validateItemName(name);
        this.description = description;
        this.price = validatePrice(price);
        this.amountInStock = validateAmountInStock(amountInStock);
    }

    private Item(UUID id, String name, String description, double price, int amountInStock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
    }

    private String validateItemName(String name) {
        if (name.isBlank() || name.isEmpty()) {
            logger.warn("The user tried to register an invalid item name: " + name);
            throw new InvalidItemNameException("The provided name is invalid");
        }
        return name;
    }

    private int validateAmountInStock(int amountInStock) {
        if (amountInStock < 0) {
            logger.warn("The user tried to register an invalid amount in stock: " + amountInStock);
            throw new InvalidStockValue("The provided amount in stock is invalid");
        }
        return amountInStock;
    }

    private double validatePrice(double price) {
        if (price <= 0.0) {
            logger.warn("The user tried to register an invalid price: " + price);
            throw new InvalidPriceException("The provided price is invalid");
        }
        return price;
    }

    public UUID getId() {
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

    public Item setName(String name) {
        this.name = validateItemName(name);
        return this;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public Item setPrice(double price) {
        this.price = validatePrice(price);
        return this;
    }

    public Item setAmountInStock(int amountInStock) {
        this.amountInStock = validateAmountInStock(amountInStock);
        return this;
    }

    public Item deepCloneItem() {
        return new Item(this.getId(),this.getName(),this.getDescription(),this.getPrice(),this.getAmountInStock());
    }
}
