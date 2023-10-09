package com.library.management.controller;

import com.library.management.persistence.model.Book;
import com.library.management.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * GET /libros: Listar todos los libros. x
 * GET /libros/{id}: Obtener detalles de un libro específico. x
 * POST /libros: Crear un nuevo libro. x
 * PUT /libros/{id}: Actualizar un libro existente. x
 * DELETE /libros/{id}: Eliminar un libro.
 */
@RestController
@RequestMapping (value = "books")
public class BookController {

    private IBookService bookService;
    private static final Logger LOG = LoggerFactory.getLogger(BookController.class);

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getHome() {
        return bookService.getBooks();
    }

    @GetMapping( "/{id}")
    public Book findOne(@PathVariable Long id) {
        if (this.bookService.findById(id).isPresent())
            return  this.bookService.findById(id).get();

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @PostMapping()
    public Book create(@RequestBody Book newBook) {
        //LOG.info("Book:  {}", newBook);
        return this.bookService.save(newBook);
    }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book newBook) {
        if (this.bookService.findById(id).isPresent())
            return this.bookService.save(newBook);
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @DeleteMapping("/{id}")
    public void delete( @PathVariable Long id) {
        if (this.bookService.findById(id).isPresent())
            this.bookService.delete(id);
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

}
