package com.library.management.persintence.repository;

import com.library.management.persintence.model.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorRepository {
    Optional<Author> findById(Long id);
    Author save(Author author);
    List<Author> getAuthors();
    void delete(Long id);
}
