package com.example.kata_book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @UuidGenerator
    private String id;

    private String title;

    private String author;

    private String borrowUser;

}
