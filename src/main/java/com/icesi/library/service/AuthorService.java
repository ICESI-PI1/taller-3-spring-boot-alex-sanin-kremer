package com.icesi.library.service;

import com.icesi.library.model.Author;
import com.icesi.library.model.Book;
import com.icesi.library.repository.AuthorRepository;
import com.icesi.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorByID(final Long authorID) throws Exception {
        Optional<Author> existingAuthorOptional = authorRepository.findById(authorID);
        this.validateExistingAuthor(existingAuthorOptional, authorID);
        return existingAuthorOptional.get();
    }

    public Author createAuthor(final Author authorToCreate) {
        return authorRepository.save(authorToCreate);
    }

    public Author updateAuthor(final Author authorToUpdate) throws Exception {
        Long authorID = authorToUpdate.getID();
        Optional<Author> existingAuthorOptional = authorRepository.findById(authorID);
        this.validateExistingAuthor(existingAuthorOptional, authorID);
        return authorRepository.save(authorToUpdate);
    }

    public void deleteAuthor(final Long authorID) throws Exception {
        Optional<Author> existingAuthorOptional = authorRepository.findById(authorID);
        this.validateExistingAuthor(existingAuthorOptional, authorID);
        authorRepository.deleteById(authorID);
    }

    public List<Book> getBooksByAuthor(final Long authorID) {
        return bookRepository.findByAuthorID(authorID);
    }

    private void validateExistingAuthor(final Optional<Author> existingAuthorOptional, final Long authorID) throws Exception {
        if (!existingAuthorOptional.isPresent()) {
            throw new Exception(String.format("The author with ID %s does not exist", authorID));
        }
    }
}
