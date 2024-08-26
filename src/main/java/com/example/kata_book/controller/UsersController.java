package com.example.kata_book.controller;

import com.example.kata_book.model.Users;
import com.example.kata_book.service.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Users> Users(@PathVariable String id) {
        Optional<Users> Users = usersService.findById(id);
        return Users.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @GetMapping
    public List<Users> list() {
        return usersService.getAll();
    }

    @PostMapping
    public String save(@RequestBody Users user) {
        return "Created user with id " + usersService.save(user);
    }

    @PutMapping("{id}")
    public void update(@PathVariable String id, @RequestBody Users user) {
        Optional<Users> Users = usersService.findById(id);
        if (Users.isPresent()) {
            usersService.update(id, user);
        } else {
            usersService.save(user);
        }
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id) {
        usersService.delete(id);
       return "Deleted the user with id " + id;
    }

}
