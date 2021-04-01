package be.willekens.template.api.dto.order;

public class ItemToShipDto {

    private String itemId;
    private String itemName;
    private String address;

    public ItemToShipDto() {
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getAddress() {
        return address;
    }

    public ItemToShipDto setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public ItemToShipDto setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

    public ItemToShipDto setAddress(String address) {
        this.address = address;
        return this;
    }
}
