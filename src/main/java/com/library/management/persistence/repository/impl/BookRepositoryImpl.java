package com.library.management.persistence.repository.impl;

import com.library.management.persistence.model.Book;
import com.library.management.persistence.repository.IBookRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements IBookRepository {
    List<Book> books = new ArrayList<>();

    @Override
    public Optional<Book> findById(Long id) {
        return books.stream().filter(b-> Objects.equals(b.getId(), id)).findFirst();
    }

    @Override
    public Book save(Book book) {
        Book existingBook = findById(book.getId()).orElse(null);
        if(existingBook == null){
            books.add(book);
        }else{
            books.remove(existingBook);
            Book newBook = new Book(book);
            books.add(newBook);

        }
        return book;
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(existingBook -> books.remove(existingBook));
    }

    @Override
    public List<Book> getBooksAuthor(Long id) {
        return books.stream().filter(b-> Objects.equals(b.getAuthor().getId(), id)).toList();
    }
}
