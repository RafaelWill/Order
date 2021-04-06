package be.willekens.template.service;

import be.willekens.template.domain.models.item.Item;
import be.willekens.template.domain.repository.ItemRepository;
import be.willekens.template.infrastructure.exceptions.DuplicateItemNameException;
import be.willekens.template.infrastructure.exceptions.ItemDoesNotExistException;
import be.willekens.template.infrastructure.exceptions.NotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    private final ItemRepository itemRepository;
    private final EmployeeService employeeService;

    public ItemService(ItemRepository itemRepository, EmployeeService employeeService) {
        this.itemRepository = itemRepository;
        this.employeeService = employeeService;
    }

    public Item getItemById(String itemId) {
        return checkIfItemExists(itemRepository.getItemById(itemId));
    }

    public Item addItem(Item item, String authorizationId) {
        if (!employeeService.isAdmin(authorizationId)) {
            logger.warn("A user with id " + authorizationId + " tried to register a new item without the right permissions");
            throw new NotAuthorizedException("You are not authorized to perform this action");
        }
        if (checkIfItemNameExists(item)) {
            logger.warn("A user tried to register a new item with the same name: " + item.getName());
            throw new DuplicateItemNameException("This name " + item.getName() + " already exists in our database");
        }
        return itemRepository.addItem(item);
    }

    public Item updateItem(String authorizationId, String itemId, Item updateItemToItem) {
        if (!employeeService.isAdmin(authorizationId)) {
            logger.warn("A user with id " + authorizationId + " tried to update an item without the right permissions");
            throw new NotAuthorizedException("You are not authorized to perform this action");
        }
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

    public Collection<Item> getItemByStockFilter(String authorizationId, String filter) {
        if (!employeeService.isAdmin(authorizationId)){
            logger.warn("A user with id " + authorizationId + " tried to request a list of items without the right permissions");
            throw new NotAuthorizedException("You are not authorized to perform this action");
        }
        return itemRepository.getItemsByStockAmount(filter);
    }
}