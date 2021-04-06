package be.willekens.template.domain.repository;

import be.willekens.template.domain.models.item.Item;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

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

    public Item updateItem(String itemId, Item updatedItem) {
        return getItemById(itemId).get()
                .setName(updatedItem.getName())
                .setDescription(updatedItem.getDescription())
                .setPrice(updatedItem.getPrice())
                .setAmountInStock(updatedItem.getAmountInStock());
    }

    public Collection<Item> getItemsByStockAmount(String filter) {
        if (filter == null) {
            return items.stream().sorted().collect(Collectors.toList());
        }
        if (filter.isEmpty() || filter.isBlank()) {
            return items.stream().sorted().collect(Collectors.toList());
        }
        return items.stream().filter(item -> item.getStockUrgency().getPrettifiedName().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
    }
}
