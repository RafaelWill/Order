package be.willekens.template.domain.repository;

import be.willekens.template.domain.models.item.Item;
import org.springframework.stereotype.Repository;

import java.util.*;

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

    public Optional<Item> getItemById(String itemId) {
        return items.stream().filter(item -> item.getId().toString().equals(itemId)).findFirst();
    }
}
