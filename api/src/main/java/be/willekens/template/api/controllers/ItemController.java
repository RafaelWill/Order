package be.willekens.template.api.controllers;

import be.willekens.template.api.dto.item.CreateItemDto;
import be.willekens.template.api.dto.item.ItemDto;
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
    public ItemDto addItem(@RequestBody CreateItemDto createItemDto) {
        logger.info("A user with id " + createItemDto.getAuthorizationId() + " is requesting permission to create a new item");
        return itemMapper.mapToDto(itemService.addItem(itemMapper.createItem(createItemDto),createItemDto.getAuthorizationId()));
    }
}
