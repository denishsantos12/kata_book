package com.example.kata_book.service;

import com.example.kata_book.model.Users;
import com.example.kata_book.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UserRepository userRepository;
    
    public UsersService(UserRepository UsersRepository) {
        this.userRepository = UsersRepository;
    }

    public Optional<Users> findById(String id) {
        return userRepository.findById(id);
    }

    public String save(Users Users) {
        return userRepository.save(Users).getId();
    }

    public void update(String id, Users users) {
        Optional<Users> Users = findById(id);
        if (Users.isPresent()) {
            Users updatedUser = Users.get();
            updatedUser.setUsername(users.getUsername());
            updatedUser.setEmail(users.getEmail());
            updatedUser.setAddress(users.getAddress());
            updatedUser.setPhone(users.getPhone());
            userRepository.save(users);
        }
    }

    public List<Users> getAll() {
        return userRepository.findAll();
    }

    public void delete(String id) {
        Optional<Users> Users = findById(id);
        Users.ifPresent(userRepository::delete);
    }
}
