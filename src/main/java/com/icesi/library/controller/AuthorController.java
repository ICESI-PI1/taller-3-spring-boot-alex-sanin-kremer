package com.icesi.library.controller;

import com.icesi.library.model.Author;
import com.icesi.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping(value = "/{authorID}")
    public ResponseEntity getAuthorById(@PathVariable(value = "authorID") Long authorID) {
        try {
            return ResponseEntity.ok(authorService.getAuthorByID(authorID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity createAuthor(@RequestBody Author authorToCreate) {
        return ResponseEntity.ok(authorService.createAuthor(authorToCreate));
    }

    @PutMapping
    public ResponseEntity updateAuthor(@RequestBody Author authorToUpdate) {
        try {
            return ResponseEntity.ok(authorService.updateAuthor(authorToUpdate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{authorID}")
    public ResponseEntity deleteBook(@PathVariable(value = "authorID") Long authorID) {
        try {
            authorService.deleteAuthor(authorID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{authorID}/books")
    public ResponseEntity getBooksByAuthor(@PathVariable(value = "authorID") Long authorID) {
        try {
            return ResponseEntity.ok(authorService.getBooksByAuthor(authorID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
