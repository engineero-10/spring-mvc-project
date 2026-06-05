package org.example.springmvcproject.controller;

import org.example.springmvcproject.entity.Instructor;
import org.example.springmvcproject.scopes.ApplicationInstructor;
import org.example.springmvcproject.services.AuthServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AuthController {
    private final ApplicationInstructor applicationInstructor;
    private final AuthServices authServices;

    public AuthController(AuthServices authServices, ApplicationInstructor applicationInstructor) {
        this.authServices = authServices;
        this.applicationInstructor = applicationInstructor;
    }

    @GetMapping({"", "/login"})
    public String login(Model model) {
        model.addAttribute("registeredNumber", applicationInstructor.getRegisteredNumber());
        model.addAttribute("instructor", new Instructor());
        return "login";
    }

    @PostMapping({"", "/login"})
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        // logic
        var loggedIn = authServices.login(email, password);
        if (loggedIn) {
            return "redirect:/home";
        }
        model.addAttribute("error", true);
        return "login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("registeredNumber", applicationInstructor.getRegisteredNumber());
        model.addAttribute("instructor", new Instructor());
        return "register";
    }

    @PostMapping("register")
    public String register(@ModelAttribute("instructor") Instructor instructor, Model model) {
        var registered = authServices.register(instructor);
        if (!registered) {
            model.addAttribute("error", true);
            return "register";
        }
        model.addAttribute("successMessage", true);
        return "redirect:/login";
    }

    @GetMapping("logout")
    public String logout(Model model) {
        authServices.logout();
        return "redirect:/login";
    }
}
