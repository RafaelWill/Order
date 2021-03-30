package be.willekens.template.domain.repository;

import be.willekens.template.domain.models.customer.Customer;
import be.willekens.template.domain.models.item.Item;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class ItemRepository {

    private final Set<Item> items = new HashSet<>();

    public Item addItem(Item item) {
        items.add(item);
        return item;
    }

    public Collection<Item> getAllItems() {
        return Collections.unmodifiableSet(items);
    }

}
