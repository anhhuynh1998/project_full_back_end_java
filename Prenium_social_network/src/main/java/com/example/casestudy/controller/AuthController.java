package com.example.casestudy.controller;

import com.example.casestudy.service.auth.AuthService;
import com.example.casestudy.service.auth.request.RegisterRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;

@AllArgsConstructor
@Controller
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String showLogin(Model model) {
        RegisterRequest user = new RegisterRequest();
        model.addAttribute("user", user);
        return "login";
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        return "index";
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        return "profile";
    }



    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // create model object to store form data
        RegisterRequest user = new RegisterRequest();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") RegisterRequest request,
                               BindingResult result, Model model) {
        authService.checkUsernameOrPhoneNumberOrEmail(request, result);
        if (result.hasErrors()) {
            return "/register";
        }
        authService.register(request);
        return "redirect:/register?success";
    }
}
