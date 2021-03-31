package be.willekens.template.api.mappers;

import be.willekens.template.api.dto.item.CreateItemDto;
import be.willekens.template.api.dto.item.ItemDto;
import be.willekens.template.api.dto.item.UpdateItemDto;
import be.willekens.template.domain.models.item.Item;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Item createItem(CreateItemDto createItemDto) {
        return new Item(createItemDto.getName(), createItemDto.getDescription(), createItemDto.getPrice(),createItemDto.getAmountInStock());
    }

    public ItemDto mapToDto(Item item) {
        return modelMapper.map(item, ItemDto.class);
//        return new ItemDto()
//                .setId(item.getId().toString())
//                .setName(item.getName())
//                .setDescription(item.getDescription())
//                .setPrice(item.getPrice())
//                .setAmountInStock(item.getAmountInStock());
    }

    public Item updateItemToItem(UpdateItemDto updateItemDto) {
        return new Item(updateItemDto.getName(), updateItemDto.getDescription(), updateItemDto.getPrice(), updateItemDto.getAmountInStock());
    }
}
