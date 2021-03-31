package be.willekens.template.api.controllers;

import be.willekens.template.api.dto.item.CreateItemDto;
import be.willekens.template.api.dto.item.ItemDto;
import be.willekens.template.api.dto.item.UpdateItemDto;
import be.willekens.template.api.mappers.ItemMapper;
import be.willekens.template.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/item")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemController(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestHeader String authorizationId, @RequestBody CreateItemDto createItemDto) {
        logger.info("A user with id " + authorizationId + " is requesting permission to create a new item");
        return itemMapper.mapToDto(itemService.addItem(itemMapper.createItem(createItemDto),authorizationId));
    }

    @PutMapping(path = "/{itemId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ItemDto updateItem(@RequestHeader String authorizationId, @PathVariable String itemId, @RequestBody UpdateItemDto updateItemDto) {
        logger.info("A user with id " + authorizationId + " is requesting to update an item with id " + itemId);
        return itemMapper.mapToDto(itemService.updateItem(authorizationId ,itemId, itemMapper.updateItemToItem(updateItemDto)));
    }
}
