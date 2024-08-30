package com.example.testrest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private int id;
    private String title;
    private String author;
    private int publicationYear;


}
