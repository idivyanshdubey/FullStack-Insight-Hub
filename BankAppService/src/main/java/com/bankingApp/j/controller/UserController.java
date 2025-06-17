package com.bankingApp.j.controller;

import com.bankingApp.j.model.User;
import com.bankingApp.j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            User savedUser = userService.signup(user);
            savedUser.setPassword(null);
            return ResponseEntity.status(201).body(savedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword())
                .map(u -> {
                    u.setPassword(null);
                    return ResponseEntity.<Object>ok(u);
                })
                .orElseGet(() -> ResponseEntity.status(401).body("Invalid username or password"));
    }
}
