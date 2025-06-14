package com.cibersecurity.login.controller;

import com.cibersecurity.login.model.User;
import com.cibersecurity.login.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Renderiza login.html
    }

    // Muestra el formulario de registro
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // renderiza register.html
    }

    // Procesa el formulario de registro
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        boolean registrado = authService.register(user);

        if (!registrado) {
            model.addAttribute("error", "El nombre de usuario ya existe");
            return "register"; // vuelve al formulario con mensaje de error
        }

        return "register-success"; // muestra la página de éxito
    }
}
