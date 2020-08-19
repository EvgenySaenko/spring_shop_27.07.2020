package ru.geekbrains.sample.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.sample.dto.ItemDTO;
import ru.geekbrains.sample.persistence.entities.Book;
import ru.geekbrains.sample.persistence.entities.Item;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    //хотим найти все элементы - именно лист ДТО получим
    List<ItemDTO>findAllBy();
}