package com.example.kata_book.repository;

import com.example.kata_book.model.Book;
import com.example.kata_book.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

   List<Book> findByBorrowUser(String borrowUser);

    Optional<Book> findByTitle(String title);
}
