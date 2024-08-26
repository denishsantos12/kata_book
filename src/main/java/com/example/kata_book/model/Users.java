package com.example.kata_book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Users {

    @Id
    private String id;

    private String username;

    private String email;

    private String phone;

    private String address;

}
