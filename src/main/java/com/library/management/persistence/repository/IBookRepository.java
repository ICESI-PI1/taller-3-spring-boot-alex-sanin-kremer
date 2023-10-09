package com.library.management.persistence.repository;

import com.library.management.persistence.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookRepository {
    Optional<Book> findById(Long id);
    Book save(Book book);
    List<Book> getBooks();
    void delete(Long id);
    List<Book> getBooksAuthor(Long id);
}
