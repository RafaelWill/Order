package be.willekens.template.api.dto.order;

import java.util.List;

public class ShippingDto {

    private List<ItemToShipDto> allOrders;

    public ShippingDto() {
    }

    public List<ItemToShipDto> getAllOrders() {
        return allOrders;
    }

    public ShippingDto setAllOrders(List<ItemToShipDto> allOrders) {
        this.allOrders = allOrders;
        return this;
    }
}
