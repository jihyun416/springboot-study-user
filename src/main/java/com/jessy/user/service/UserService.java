package com.jessy.user.service;

import com.jessy.user.domain.User;
import com.jessy.user.repository.UserQuerydslRepository;
import com.jessy.user.repository.UserRepository;
import com.jessy.user.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    public final UserRepository userRepository;
    public final UserQuerydslRepository userQuerydslRepository;


    public User findUser(String userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

    public List<UserDTO> findUserList(UserDTO userDTO) {
        return userRepository.findUserList(userDTO);
    }

    public List<UserDTO> findUserList2(UserDTO userDTO) {
        return userQuerydslRepository.findUserList(userDTO);
    }
}
