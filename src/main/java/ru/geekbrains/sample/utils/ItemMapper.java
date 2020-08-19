package ru.geekbrains.sample.utils;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.sample.dto.v1.ItemDTOV1;
import ru.geekbrains.sample.persistence.entities.Item;

import java.util.List;

@Mapper//означает что этот класс является конвертером
public interface ItemMapper {

    ItemMapper MAPPER = Mappers.getMapper(ItemMapper.class);

    //из дто в полноценный Item
    Item toItem(ItemDTOV1 itemDTOV1);

    //из полноценного Item  в дто
    @InheritInverseConfiguration
    ItemDTOV1 fromItem(Item item);

    //из листа дто-шек собираем полноценные список Items
    List<Item> toItemList(List<ItemDTOV1> itemDTOV1s);

    //из листа полноценных сущностей в дто-шки
    List<ItemDTOV1> fromItemList(List<Item> items);
}
