package ru.geekbrains.sample.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.geekbrains.sample.dto.ItemDTO;
import ru.geekbrains.sample.dto.v1.ItemDTOV1;
import ru.geekbrains.sample.exceptions.ItemNotFoundExceptions;
import ru.geekbrains.sample.persistence.entities.Item;
import ru.geekbrains.sample.services.ItemService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/items")
public class RestItemController {

    private ItemService itemService;

    @Autowired
    public RestItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getAllItems(){//вернет список
        return itemService.getAllItems();
    }

    @GetMapping("/dto")
    public List<ItemDTO> getAllItemsDTO(){//вернет список элементов с нужными нам полями
        return itemService.getAllItemsDTO();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable String id){//вернет один элемент по id
        //если продукт не существует - бросаем ексепшен
        if (!itemService.existsById(UUID.fromString(id))){
            throw new ItemNotFoundExceptions("Item not found, id: " + id);
        }//в другом случае вернем сам продукт и статус код 200
        return new ResponseEntity<>(itemService.getOneItem(UUID.fromString(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public String deleteOneItem(@PathVariable String id){//удалит элемент по id
        itemService.deleteById(UUID.fromString(id));
        return "Ok";
    }

    @PostMapping//при сохранении это статус CREATED (статус код 201) и можем указать аннотацию
    @ResponseStatus(HttpStatus.CREATED)
    public Item saveNewItem(@RequestBody Item item){//сохраним новый элемент(добавим)
        //занулим id если кто-нибудь решит прописывать id (незная что у нас id генератор)
        if (item.getId() != null){
            item.setId(null);
        }//и метод будет сохранять его как новый объект давая ему наш новый id
        return itemService.saveOrUpdate(item);
    }

//    @PutMapping
//    public Item modifyItem(@RequestBody Item item){//сохраним новый элемент(добавим)
//        //если мы не нашли продукт который нужно модифицировать, то кидаем исключение
//       if (item.getId() == null || !itemService.existsById(item.getId())){
//           throw new ItemNotFoundExceptions("Item not found, id: " + item.getId());
//       }
//        return itemService.saveOrUpdate(item);
//    }

    @PutMapping//используем более продвинутое управление ответами
    //ResponseEntity добавляет больше возможностей по управлению ответом
    //если я элемент сохранил/модифицировал - я должен его вернуть в обратку и статус код 200(HttpStatus.OK)
    public ResponseEntity<?> modifyItemWithStatusCode(@RequestBody Item item){//сохраним новый элемент(добавим)
        //если мы не нашли продукт который нужно модифицировать, то кидаем исключение
        if (item.getId() == null || !itemService.existsById(item.getId())){
            throw new ItemNotFoundExceptions("Item not found, id: " + item.getId());
        }
        //если пытаются сохранить указав цену элемента меньше нуля, вернем клиенту статус код и текст почему так
        if (item.getPrice() <= 0){//типо цена не может быть <= 0
            return new ResponseEntity<>("Item's price can not be negative",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemService.saveOrUpdate(item), HttpStatus.OK);
    }

    //создадим обработчик исключений и настроим его
    //если у нас будет вылетать ItemNotFoundExceptions или любой из его наследников, мы его перехватим
    //обернем в ResponseEntity<?> туда закинем(сообщение из нашего эксепшена, статус код 404)
    @ExceptionHandler
    public ResponseEntity<?> handleException(ItemNotFoundExceptions exc){
        return new ResponseEntity<>(exc.getMessage(),HttpStatus.NOT_FOUND);
    }
    //еще есть @ControllerAdvice используется для глобальной обработки ошибок в приложении Spring MVC.
    // Он также имеет полный контроль над телом ответа и кодом состояния.
}
