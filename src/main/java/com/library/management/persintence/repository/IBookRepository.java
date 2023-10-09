package com.library.management.persintence.repository;

import com.library.management.persintence.model.Book;

import java.util.Optional;

public interface IBookRepository {
    Optional<Book> findById(Long id);
    Book save(Book book);
    void delete(Long id);
}
