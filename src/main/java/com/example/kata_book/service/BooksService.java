package com.example.kata_book.service;

import com.example.kata_book.model.Book;
import com.example.kata_book.model.Users;
import com.example.kata_book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BookRepository bookRepository;
    
    public BooksService(BookRepository BookRepository) {
        this.bookRepository = BookRepository;
    }

    public Optional<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public String save(Book book) {
        return bookRepository.save(book).getId();
    }

    public void update(Book book) {
        Optional<Book> Book = findById(book.getId());
        if (Book.isPresent()) {
            Book updatedBook = Book.get();
            updatedBook.setTitle(updatedBook.getTitle());
            updatedBook.setAuthor(updatedBook.getAuthor());
            updatedBook.setBorrowUser(updatedBook.getBorrowUser());
            bookRepository.save(updatedBook);
        }
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public void delete(String id) {
        Optional<Book> Book = findById(id);
        Book.ifPresent(bookRepository::delete);
    }

    public Optional<Book> findByTitle(String title) {
       return bookRepository.findByTitle(title);
    }

    public List<Book> findByBorrowUser(String userId) {
       return bookRepository.findByBorrowUser(userId);
    }
}
