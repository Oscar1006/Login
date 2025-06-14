package com.cibersecurity.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cibersecurity.login.model.User;
import com.cibersecurity.login.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/profile")
    public String showUserProfile(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("lastLogin", user.getLastLogin());
        model.addAttribute("role", userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority()).findFirst().orElse("ROLE_USER"));
        return "profile"; // renderiza profile.html
    }


    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword,
                            @RequestParam String newPassword,
                            Model model,
                            Authentication authentication) {

        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow();

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("error", "La contraseña actual no es correcta");
            return "profile";
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        model.addAttribute("success", "Contraseña actualizada correctamente");
        model.addAttribute("username", user.getUsername());
        model.addAttribute("lastLogin", user.getLastLogin());
        model.addAttribute("role", user.getRole());
        return "profile"; // vuelve al perfil con mensaje de éxito
    }
}
