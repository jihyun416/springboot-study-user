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

    public User createUser(User user) {
        user.setUserSeq(null);
        return userRepository.save(user);
    }

    public User updateUser(Long userSeq, User user) {
        user.setUserSeq(userSeq);
        return userRepository.save(user);
    }

    public ResponseDTO deleteUser(Long userSeq) {
        User user = userRepository.findById(userSeq).orElseThrow(()->new NoDataException("No Data. userSeq:"+userSeq));
        userRepository.delete(user);
        return ResponseDTO.builder().result(true).status(HttpStatus.OK.value()).message("Deleted successfully!").build();
    }
}
