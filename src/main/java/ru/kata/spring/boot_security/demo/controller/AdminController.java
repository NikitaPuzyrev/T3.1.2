package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class AdminController {
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "userlist";
    }

    @GetMapping("/userCreate")
    public String createUserForm(User user) {
        return "userCreate";
    }

    @PostMapping("userCreate")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("userDelete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("userUpdate/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);

        model.addAttribute("user", user);
        return "/userUpdate";
    }

    @PostMapping("/userUpdate")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }
}
