package com.jessy.user.service;

import com.jessy.user.domain.User;
import com.jessy.user.exception.NoDataException;
import com.jessy.user.repository.UserQuerydslRepository;
import com.jessy.user.repository.UserRepository;
import com.jessy.user.web.dto.ResponseDTO;
import com.jessy.user.web.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    public final UserRepository userRepository;
    public final UserQuerydslRepository userQuerydslRepository;

    @Transactional(readOnly = true)
    public User findUser(String userId) {
        return userRepository.findByUserIdEquals(userId).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findUserList(UserDTO userDTO) {
        return userRepository.findUserList(userDTO);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findUserList2(UserDTO userDTO) {
        return userQuerydslRepository.findUserList(userDTO);
    }

    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(String userId, User user) {
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Transactional
    public ResponseDTO deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new NoDataException("No Data. userId:"+userId));
        userRepository.delete(user);
        return ResponseDTO.builder().result(true).status(HttpStatus.OK.value()).message("Deleted successfully!").build();
    }
}
