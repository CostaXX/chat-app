package com.cwa.chatapp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.cwa.chatapp.entity.User;
import com.cwa.chatapp.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    private final String[] colors = {
        "#FF5733", "#33FF57", "#3357FF", "#F333FF", "#33FFF5",
        "#F5FF33", "#FF33A8", "#A833FF", "#33FFA8", "#FFA833"
    };

    public User createOrGetUser(String username) {
        var existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            var user = existingUser.get();
            user.setOnline(true);
            user.setLastSeen(LocalDateTime.now());
            return userRepository.save(user);
        }

        var randomColor = colors[new Random().nextInt(colors.length)];
        var newUser = new User(username, randomColor);
            newUser.setOnline(true);
            newUser.setLastSeen(LocalDateTime.now());   
        return userRepository.save(newUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public List<User> getOnlineUsers() {
        return userRepository.findByIsOnlineTrue();
    }

    public void setUserOffline(User user) {
        user.setOnline(false);
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
