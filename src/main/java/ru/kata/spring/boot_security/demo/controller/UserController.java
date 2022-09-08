package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping("userPage/{id}")
    public String updatePageForm(@PathVariable("id") int id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "/userPage";
    }

    @GetMapping("myPage")
    public String pageUserForm(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        return "redirect:/userPage/" + user.getId();
    }

    @PostMapping("/usePageUpdate")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/userPage/" + user.getId();
    }
}


