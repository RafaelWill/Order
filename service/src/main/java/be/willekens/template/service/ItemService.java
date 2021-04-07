package be.willekens.template.service;

import be.willekens.template.domain.models.item.Item;
import be.willekens.template.domain.repository.ItemRepository;
import be.willekens.template.infrastructure.exceptions.DuplicateItemNameException;
import be.willekens.template.infrastructure.exceptions.ItemDoesNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item getItemById(String itemId) {
        return checkIfItemExists(itemRepository.getItemById(itemId));
    }

    public Item addItem(Item item) {
        if (checkIfItemNameExists(item)) {
            logger.warn("A user tried to register a new item with the same name: " + item.getName());
            throw new DuplicateItemNameException("This name " + item.getName() + " already exists in our database");
        }
        return itemRepository.addItem(item);
    }

    public Item updateItem(String itemId, Item updateItemToItem) {
        checkIfItemExists(itemRepository.getItemById(itemId));
        return itemRepository.updateItem(itemId, updateItemToItem);
    }

    private boolean checkIfItemNameExists(Item newItem) {
        return itemRepository.getAllItems().stream()
                .anyMatch(item -> item.getName().equalsIgnoreCase(newItem.getName()));
    }

    private Item checkIfItemExists(Optional<Item> item) {
        if (item.isEmpty()) {
            logger.warn("The requested item was not found");
            throw new ItemDoesNotExistException("No item was found");
        }
        return item.get();
    }

    public Collection<Item> getItemByStockFilter(String filter) {
        return itemRepository.getItemsByStockAmount(filter);
    }
}