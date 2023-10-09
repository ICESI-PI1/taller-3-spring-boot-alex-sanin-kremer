package com.library.management.persintence.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {
    //Book class
    private Long id;
    private String name;
    private String publisher;
    private String author;
    private double price;
    private int stock;

    public Book(Book book) {
        this(book.getId(), book.getName(), book.getPublisher(), book.getAuthor(), book.getPrice(), book.getStock());
    }
}