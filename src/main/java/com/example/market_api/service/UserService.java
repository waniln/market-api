package com.example.market_api.service;

import com.example.market_api.entity.User;
import com.example.market_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void register(String email, String password, String nickname) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 사용중인 이메일입니다.");
        }
        if (userRepository.existsByNickname(nickname)) {
            throw new RuntimeException("이미 사용중인 닉네임입니다.");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickname(nickname);

        userRepository.save(user);
    }

     public User login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("이메일 또는 비밀번호가 틀렸습니다.");
        }

        User user = optionalUser.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("이메일 또는 비밀번호가 틀렸습니다.");
        }

        return user;
    }
}