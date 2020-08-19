package ru.geekbrains.sample.exceptions;

public class ItemNotFoundExceptions extends RuntimeException {
    public ItemNotFoundExceptions(String message) {
        super(message);
    }
}
