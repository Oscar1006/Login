package com.cibersecurity.login.controller;

import com.cibersecurity.login.model.User;
import com.cibersecurity.login.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String showAdminPanel(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "admin-panel";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/reset-password/{id}")
    @Transactional
    public String resetPassword(@PathVariable Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setPassword("");
        });
        return "redirect:/admin";
    }
}
