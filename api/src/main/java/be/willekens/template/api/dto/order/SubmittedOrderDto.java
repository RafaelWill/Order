package be.willekens.template.api.dto.order;

import java.util.Collection;

public class SubmittedOrderDto {

    private Collection<OrderedItemGroupDto> orderedItems;

    public SubmittedOrderDto() {
    }

    public Collection<OrderedItemGroupDto> getOrderedItems() {
        return orderedItems;
    }
}
