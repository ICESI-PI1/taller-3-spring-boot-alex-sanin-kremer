package com.library.management.service;

import com.library.management.persistence.model.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    Optional<Author> findById(Long id);
    Author save(Author author);
    void delete(Long id);
    List<Author> getAuthors();
}
