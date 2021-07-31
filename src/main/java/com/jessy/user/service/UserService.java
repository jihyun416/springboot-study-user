package com.jessy.user.service;

import com.jessy.user.domain.User;
import com.jessy.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    public final UserRepository userRepository;

    public User findUser(String userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }
}
