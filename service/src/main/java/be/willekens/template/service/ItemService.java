package be.willekens.template.service;

import be.willekens.template.domain.models.item.Item;
import be.willekens.template.domain.repository.ItemRepository;
import be.willekens.template.infrastructure.exceptions.DuplicateItemNameException;
import be.willekens.template.infrastructure.exceptions.ItemDoesNotExistException;
import be.willekens.template.infrastructure.exceptions.NotAuthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        if (!isAdmin(authorizationId)) {
            logger.warn("A user tried with id " + authorizationId + " to register a new item without the right permissions");
            throw new NotAuthorizedException("You are not authorized to perform this action");
        }
        if (checkIfItemNameExists(item)) {
            logger.warn("A user tried to register a new item with the same name: " + item.getName());
            throw new DuplicateItemNameException("This name " + item.getName() + " already exists in our database");
        }
        return itemRepository.addItem(item);
    }

    private boolean isAdmin(String authorizationId) {
        return employeeService.getAllEmployees().stream()
                .anyMatch(id -> id.getId().toString().equals(authorizationId));
    }

    private boolean checkIfItemNameExists(Item newItem) {
        return itemRepository.getAllItems().stream()
                .anyMatch(item -> item.getName().equalsIgnoreCase(newItem.getName()));
    }

    private Item checkIfItemExists(Optional<Item> item) {
        if (item.isEmpty()) {
            logger.warn("No item was found");
            throw new ItemDoesNotExistException("You can only order items that exist within our database");
        }
        return item.get();
    }
}
