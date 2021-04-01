package be.willekens.template.api.dto.order;

import java.util.List;

public class SubmittedOrderDto {

    private List<OrderedItemGroupDto> orderedItems;

    public SubmittedOrderDto() {
    }

    public List<OrderedItemGroupDto> getOrderedItems() {
        return orderedItems;
    }
}
