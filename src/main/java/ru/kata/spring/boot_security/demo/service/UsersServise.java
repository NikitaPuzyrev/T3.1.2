package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UsersServise {
    List<User> getAllUsers();

    User findById(int id);

    void saveUser(User user);

    void deleteById(int id);

    void updateUser(int id, User updatedUser);

    User findByUsername(String username);
}

