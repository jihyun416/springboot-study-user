package com.jessy.user.web.rest;

import com.jessy.user.domain.User;
import com.jessy.user.service.UserService;
import com.jessy.user.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public User findUser(@PathVariable("userId") String userId) {
        return userService.findUser(userId);
    }

    @GetMapping("/list")
    public List<UserDTO> findUserList(UserDTO userDTO) {
        return userService.findUserList(userDTO);
    }

    @GetMapping("/list2")
    public List<UserDTO> findUserList2(UserDTO userDTO) {
        return userService.findUserList2(userDTO);
    }
}
