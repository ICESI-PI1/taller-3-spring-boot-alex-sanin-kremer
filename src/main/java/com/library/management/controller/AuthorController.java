package com.library.management.controller;

import com.library.management.persistence.model.Author;
import com.library.management.persistence.model.Book;
import com.library.management.service.IAuthorService;
import com.library.management.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * GET /autores: Listar todos los autores.
 * GET /autores/{id}: Obtener detalles de un autor específico.
 * POST /autores: Crear un nuevo autor.
 * PUT /autores/{id}: Actualizar un autor existente.
 * DELETE /autores/{id}: Eliminar un autor.
 * GET /autores/{id}/libros: Listar los libros de un autor específico.
 */

@RestController
@RequestMapping(value = "authors")
public class AuthorController {

    private IAuthorService authorService;
    private IBookService bookService;
    private static final Logger LOG = LoggerFactory.getLogger(AuthorController.class);

    public AuthorController(IAuthorService authorService, IBookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public List<Author> getHome() {
        return authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public Author findOne(@PathVariable Long id) {
        if (this.authorService.findById(id).isPresent())
            return this.authorService.findById(id).get();

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @PostMapping()
    public Author create(@RequestBody Author newAuthor) {
        //LOG.info("Author:  {}", newAuthor);
        return this.authorService.save(newAuthor);
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable Long id, @RequestBody Author newAuthor) {
        if (this.authorService.findById(id).isPresent())
            return this.authorService.save(newAuthor);
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        if (this.authorService.findById(id).isPresent())
            this.authorService.delete(id);
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @GetMapping("/{id}/books")
    public List<Book> findOneBooks(@PathVariable Long id) {
        if (this.authorService.findById(id).isPresent()){
            return  bookService.getBooksAuthor(id);
        }else{
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
        }

    }

}
