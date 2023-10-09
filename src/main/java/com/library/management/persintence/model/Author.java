package com.library.management.persintence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author {
    //Author class
    private Long id; //autoincremental?
    private String name;
    private String nationality;


    public Author(Author author) {
        this(author.getId(), author.getName(), author.getNationality());
    }
}
