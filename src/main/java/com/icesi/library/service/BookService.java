package com.icesi.library.service;

import com.icesi.library.model.Book;
import com.icesi.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByID(final Long bookID) throws Exception {
        Optional<Book> existingBookOptional = bookRepository.findById(bookID);
        this.validateExistingBook(existingBookOptional, bookID);
        return existingBookOptional.get();
    }

    public Book createBook(final Book bookToCreate) {
        return bookRepository.save(bookToCreate);
    }

    public Book updateBook(final Book bookToUpdate) throws Exception {
        Long bookID = bookToUpdate.getID();
        Optional<Book> existingBookOptional = bookRepository.findById(bookID);
        this.validateExistingBook(existingBookOptional, bookID);
        return this.bookRepository.save(bookToUpdate);
    }

    public void deleteBook(final Long bookID) throws Exception {
        Optional<Book> existingBookOptional = bookRepository.findById(bookID);
        this.validateExistingBook(existingBookOptional, bookID);
        bookRepository.deleteById(bookID);
    }

    private void validateExistingBook(final Optional<Book> existingBookOptional, final Long bookID) throws Exception {
        if (!existingBookOptional.isPresent()) {
            throw new Exception(String.format("The book with ID %s does not exist", existingBookOptional));
        }
    }
}
