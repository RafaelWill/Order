package be.willekens.template.api.mappers;

import be.willekens.template.api.dto.item.CreateItemDto;
import be.willekens.template.api.dto.item.ItemDto;
import be.willekens.template.api.dto.item.UpdateItemDto;
import be.willekens.template.domain.models.item.Item;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemMapper {

    /*--- Creating & updating Item ---*/
    public Item createItem(CreateItemDto createItemDto) {
        return new Item(createItemDto.getName(), createItemDto.getDescription(), createItemDto.getPrice(),createItemDto.getAmountInStock());
    }

    public Item updateItemToItem(UpdateItemDto updateItemDto) {
        return new Item(updateItemDto.getName(), updateItemDto.getDescription(), updateItemDto.getPrice(), updateItemDto.getAmountInStock());
    }

    /*--- Creating ItemDto ---*/
    public ItemDto mapToDto(Item item) {
        return new ItemDto()
                .setId(item.getId().toString())
                .setName(item.getName())
                .setDescription(item.getDescription())
                .setPrice(item.getPrice())
                .setAmountInStock(item.getAmountInStock());
    }

    public List<ItemDto> mapListToDto(Collection<Item> items) {
        return items.stream().map(this::mapToDto).collect(Collectors.toList());
    }
}
