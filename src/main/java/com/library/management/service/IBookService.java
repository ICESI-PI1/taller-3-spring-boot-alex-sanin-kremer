package com.library.management.service;

import com.library.management.persistence.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Optional<Book> findById(Long id);
    Book save(Book book);
    void delete(Long id);
    List<Book> getBooks();
}
