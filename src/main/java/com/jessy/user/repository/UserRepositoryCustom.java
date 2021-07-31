package com.jessy.user.repository;

import com.jessy.user.web.dto.UserDTO;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDTO> findUserList(UserDTO userDTO);
}
