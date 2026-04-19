package com.example.market_api.controller;

import com.example.market_api.entity.User;
import com.example.market_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> body) {
        try {
            userService.register(
                body.get("email"),
                body.get("password"),
                body.get("nickname")
            );
            return ResponseEntity.status(201).body(Map.of("message", "회원가입 성공!"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            User user = userService.login(
                body.get("email"),
                body.get("password")
            );
            return ResponseEntity.ok(Map.of("message", "로그인 성공!", "userId", user.getId()));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(Map.of("message", e.getMessage()));
        }
    }
}