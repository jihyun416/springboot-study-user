package com.jessy.user.repository;

import com.jessy.user.web.rest.dto.UserDTO;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDTO> findUserList(UserDTO userDTO);
}
