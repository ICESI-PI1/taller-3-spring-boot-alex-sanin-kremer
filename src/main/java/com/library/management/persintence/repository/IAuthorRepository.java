package com.library.management.persintence.repository;

import com.library.management.persintence.model.Author;
import java.util.Optional;

public interface IAuthorRepository {
    Optional<Author> findById(Long id);
    Author save(Author author);
    void delete(Long id);
}
