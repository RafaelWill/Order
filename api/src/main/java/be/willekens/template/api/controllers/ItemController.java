package be.willekens.template.api.controllers;

import be.willekens.template.api.dto.item.CreateItemDto;
import be.willekens.template.api.dto.item.ItemDto;
import be.willekens.template.api.dto.item.UpdateItemDto;
import be.willekens.template.api.mappers.ItemMapper;
import be.willekens.template.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PreAuthorize("hasAuthority('CREATE_ITEM')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto addItem(@RequestBody CreateItemDto createItemDto) {
        logger.info("An admin is requesting to create a new item");
        return itemMapper.mapToDto(itemService.addItem(itemMapper.createItem(createItemDto)));
    }

    @PreAuthorize("hasAuthority('UPDATE_ITEM')")
    @PutMapping(path = "/{itemId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ItemDto updateItem(@PathVariable String itemId, @RequestBody UpdateItemDto updateItemDto) {
        logger.info("An admin is requesting to update an item with id " + itemId);
        return itemMapper.mapToDto(itemService.updateItem(itemId, itemMapper.updateItemToItem(updateItemDto)));
    }

    @PreAuthorize("hasAuthority('CHECK_STOCK')")
    @GetMapping(path = "/stock", produces = "application/json" )
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDto> getItemByStockAmount(@RequestParam(required = false) String filter) {
        logger.info(("An admin is requesting to get a list of items based on their stock"));
        return itemMapper.mapListToDto(itemService.getItemByStockFilter(filter));
    }
}
