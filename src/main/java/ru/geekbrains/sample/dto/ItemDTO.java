package ru.geekbrains.sample.dto;

//создадим проекцию, и если будем запрашивать вывод данных по ней, объекты будут возвращаться не с кучей полей
//которые нам не нужны, а только с двумя полями - наименование и цена
public interface ItemDTO {
    String getName();
    Integer getPrice();
}
