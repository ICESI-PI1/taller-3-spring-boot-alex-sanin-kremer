package com.icesi.library.controller;

import com.icesi.library.model.Book;
import com.icesi.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping(value = "/{bookID}")
    public ResponseEntity getBookById(@PathVariable(value = "bookID") Long bookID) {
        try {
            return ResponseEntity.ok(bookService.getBookByID(bookID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity createBook(@RequestBody Book bookToCreate) {
        return ResponseEntity.ok(bookService.createBook(bookToCreate));
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody Book bookToUpdate) {
        try {
            return ResponseEntity.ok(bookService.updateBook(bookToUpdate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{bookID}")
    public ResponseEntity deleteBook(@PathVariable(value = "bookID") Long bookID) {
        try {
            bookService.deleteBook(bookID);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
