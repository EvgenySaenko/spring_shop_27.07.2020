package ru.geekbrains.sample.controllers.v1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.sample.dto.v1.BookDTOV1;
import ru.geekbrains.sample.persistence.entities.Book;
import ru.geekbrains.sample.services.BookService;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookControllerV1 {

    private final BookService bookService;

    @GetMapping("/{id}")
    public String getSingleBook(@PathVariable String id, Model model){
        model.addAttribute("book",bookService.getOne(UUID.fromString(id)));
        return "book";
    }

    @GetMapping
    public String getAllBook(@PathVariable String id, Model model){
        model.addAttribute("book",bookService.getAllBooks());
        return "book";
    }
    @PostMapping
    public void createOneBook(@RequestBody BookDTOV1 bookDTOV1){
        bookService.saveOneBookV1(bookDTOV1);
    }

}
