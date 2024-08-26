package com.example.kata_book.controller;

import com.example.kata_book.model.Book;
import com.example.kata_book.model.Users;
import com.example.kata_book.service.BooksService;
import com.example.kata_book.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BooksService booksService;
    private final UsersService usersService;

    public BookController(BooksService booksService, UsersService usersService) {
        this.booksService = booksService;
        this.usersService = usersService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> Book(@PathVariable String id) {
        Optional<Book> Book = booksService.findById(id);
        return Book.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @GetMapping
    public List<Book> list() {
        return booksService.getAll();
    }

    @GetMapping("/title")
    public Book list(@RequestParam String title) {
        Optional<Book> book = booksService.findByTitle(title);
        return book.orElse(null);
    }

    @PostMapping
    public String save(@RequestBody Book book) {
        return "Created book " + book.getTitle() + " with id " + booksService.save(book);
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @RequestBody Book book) {
        Optional<Book> Book = booksService.findById(id);
        if (Book.isPresent()) {
            booksService.update(book);
        } else {
            booksService.save(book);
        }
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id) {
        booksService.delete(id);
        return "Deleted the book with id " + id;
    }

    @PutMapping("/borrow")
    public String borrowBook(@RequestParam String userId, @RequestParam String bookTitle) {
        Optional<Users> user = usersService.findById(userId);
        if (user.isPresent()) {
            Optional<Book> book = booksService.findByTitle(bookTitle);
            if (book.isPresent()) {
                Book borrowBook = book.get();
                borrowBook.setBorrowUser(user.get().getId());
                booksService.update(borrowBook);
            } else {
                return "User not found";
            }
        } else {
            return "User not found";
        }
       return "the book " + bookTitle + " has been borrowed to the user " + user.get().getUsername();
    }

    @PutMapping("/return")
    public String returnBook(@RequestParam String userId, @RequestParam String bookTitle) {
        Optional<Users> user = usersService.findById(userId);
        if (user.isPresent()) {
            Optional<Book> book = booksService.findByTitle(bookTitle);
            if (book.isPresent()) {
                book.get().setBorrowUser(null);
                booksService.update(book.get());
            } else {
                return "User not found";
            }
        } else {
            return "User not found";
        }
        return "the book " + bookTitle + " has been returned from the user " + user.get().getUsername();
    }

    @GetMapping("/borrow/user")
    public List<Book> listBooksBorrowByUser(@RequestParam String userId) {
        return booksService.findByBorrowUser(userId);
    }
}
