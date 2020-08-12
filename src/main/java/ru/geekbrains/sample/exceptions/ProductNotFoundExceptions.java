package ru.geekbrains.sample.exceptions;

public class ProductNotFoundExceptions extends RuntimeException {
    public ProductNotFoundExceptions(String message) {
        super(message);
    }
}
