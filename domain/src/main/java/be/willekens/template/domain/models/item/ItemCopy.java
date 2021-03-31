package be.willekens.template.domain.models.item;

import java.util.UUID;

public class ItemCopy {

    private final UUID id;
    private final String name;
    private final String description;
    private final double price;

    public ItemCopy(UUID id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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
}
