package ru.gb.hw8.controllers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.gb.hw8.aop.TrackUserAction;
import ru.gb.hw8.service.UserService;

@Controller
@AllArgsConstructor
public class MainController {
    UserService userService;

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("users", userService.getAll());
        return "main";
    }

    @GetMapping("/login")
    @TrackUserAction
    public String login() {
        return "login";
    }
}
