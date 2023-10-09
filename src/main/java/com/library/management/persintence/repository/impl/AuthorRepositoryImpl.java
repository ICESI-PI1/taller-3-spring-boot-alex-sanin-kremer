package com.library.management.persintence.repository.impl;

import com.library.management.persintence.model.Author;
import com.library.management.persintence.model.Book;
import com.library.management.persintence.repository.IAuthorRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements IAuthorRepository {
    List<Author> authors = new ArrayList<>();

    @Override
    public Optional<Author> findById(Long id) {
        return authors.stream().filter(b-> Objects.equals(b.getId(), id)).findFirst();
    }

    @Override
    public Author save(Author author) {
        Author existingAuthor = findById(author.getId()).orElse(null);
        if(existingAuthor == null){
            authors.add(author);
        }else{
            authors.remove(existingAuthor);
            Author newAuthor = new Author(author);
            authors.add(newAuthor);

        }
        return author;
    }

    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public void delete(Long id) {
        findById(id).ifPresent(existingAuthor -> authors.remove(existingAuthor));
    }
}
