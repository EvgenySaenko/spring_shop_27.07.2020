package ru.geekbrains.sample.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.sample.dto.v1.BookDTOV1;
import ru.geekbrains.sample.dto.v1.ItemDTOV1;
import ru.geekbrains.sample.persistence.entities.Book;
import ru.geekbrains.sample.persistence.entities.Item;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class Converter {

    public Book convertDtoToEntity(BookDTOV1 bookDTOV1){
        return Book.builder()
                .available(bookDTOV1.isAvailable())
                .created(new Date())
                .description(bookDTOV1.getDescription())
                .name(bookDTOV1.getName())
                .build();
    }

    //соберем ДТО шку так как нам нужно(без поля в наличии)
    public Item convertDtoToEntity(ItemDTOV1 itemDTOV1){
        return Item.builder()
                .name(itemDTOV1.getName())
                .price(itemDTOV1.getPrice())
                .build();
    }
}
