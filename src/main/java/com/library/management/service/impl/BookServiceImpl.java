package com.library.management.service.impl;

import com.library.management.persintence.model.Book;
import com.library.management.persintence.repository.IBookRepository;
import com.library.management.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    private final IBookRepository bookRepository;

    public BookServiceImpl(IBookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.getBooks();
    }
}
