package ru.gb.hw8.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.hw8.User;
import ru.gb.hw8.service.UserService;

@Controller
@AllArgsConstructor
public class RegisterController {
    UserService userService;

    @GetMapping("/register")
    public String registerUser(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("User") User user, Model model) {
        userService.saveUser(user);
        return ("redirect:/login");
    }
}
