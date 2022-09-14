package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
@Repository
public interface UserDao {
    List<User> getAllUsers();

    User findById(int id);

    void saveUser(User user);

    void deleteById(int id);

    User findByUsername(String username);
}
