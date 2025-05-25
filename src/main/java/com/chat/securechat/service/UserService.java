package com.chat.securechat.service;


import com.chat.securechat.entity.User;
import com.chat.securechat.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // / Конструктор — Spring сам подставит зависимости
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // // Проверка: занят ли логин
    public boolean isUsernameTaken(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    // Регистрация нового пользователя
    public boolean registerUser(String username, String rawPassword){
        if (isUsernameTaken(username)){
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole("USER");

        userRepository.save(user);
        return true;

    }

    // Проверка входа (логин)
    public Optional<User> authenticate(String username, String rawPassword){
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(rawPassword, user.getPassword()));
    }

}
