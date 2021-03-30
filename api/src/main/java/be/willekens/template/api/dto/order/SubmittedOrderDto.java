package be.willekens.template.api.dto.order;

import java.util.Collection;

public class SubmittedOrderDto {

    private String customerId;
    private Collection<OrderedItemGroupDto> orderedItems;

    public SubmittedOrderDto() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public Collection<OrderedItemGroupDto> getOrderedItems() {
        return orderedItems;
    }
}
