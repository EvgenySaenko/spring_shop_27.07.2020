package ru.geekbrains.sample.utils;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.sample.dto.v1.BookDTOV1;
import ru.geekbrains.sample.persistence.entities.Book;

import java.util.List;

@Mapper//означает что этот класс является конвертером
public interface BookMapper {

    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    Book toBook(BookDTOV1 bookDTOV1);//из дто в книгу

    @InheritInverseConfiguration
    BookDTOV1 fromBook(Book book);//из книжки в дто

    List<Book> toBookList(List<BookDTOV1> bookDTOV1s);

}
