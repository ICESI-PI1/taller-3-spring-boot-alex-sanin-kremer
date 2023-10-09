package com.library.management.persintence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    //Book class
    private Long id; //autoincremental????
    private String title;
    private Date datePublished;
    private Author author;


    public Book(Book book) {
        this(book.getId(), book.getTitle(), book.getDatePublished(),book.getAuthor());
    }
}