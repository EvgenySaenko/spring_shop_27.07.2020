package ru.geekbrains.sample.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.sample.dto.ItemDTO;
import ru.geekbrains.sample.dto.v1.BookDTOV1;
import ru.geekbrains.sample.dto.v1.ItemDTOV1;
import ru.geekbrains.sample.dto.v2.BookDTOV2;
import ru.geekbrains.sample.persistence.entities.Book;
import ru.geekbrains.sample.persistence.entities.Item;
import ru.geekbrains.sample.persistence.repositories.BookRepository;
import ru.geekbrains.sample.persistence.repositories.ItemRepository;
import ru.geekbrains.sample.utils.Converter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final Converter converter;
    private final ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getOneItem(UUID id) {
        return itemRepository.findById(id).orElse(null);
    }

    //чтобы сервисы не версионировать - мы версионируем методы
    public Item saveOneItemV1(ItemDTOV1 itemDTOV1){
        return itemRepository.save(converter.convertDtoToEntity(itemDTOV1));

    }

    //удаление по id
    public void deleteById(UUID id){
        itemRepository.deleteById(id);
    }

    //сохранение / изменение (for POST and PUT)
    public Item saveOrUpdate(Item item){
        return itemRepository.save(item);
    }

    //проверим существует ли данный элемент
    public boolean existsById(UUID id){
        return itemRepository.existsById(id);
    }
    //запросим список элементов в проекции ДТО то есть с нужными нам полями
    public List<ItemDTO> getAllItemsDTO(){
        return itemRepository.findAllBy();
    }

}
