package com.jessy.user.web.rest;

import com.jessy.user.domain.User;
import com.jessy.user.service.UserService;
import com.jessy.user.web.dto.ResponseDTO;
import com.jessy.user.web.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name="02. User", description = "사용자")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    public final ModelMapper modelMapper;
    private final UserService userService;

    @Operation(summary  = "사용자 정보", description = "사용자 정보를 가져온다.")
    @GetMapping("/{userId}")
    public UserDTO findUser(@Parameter(description = "사용자 아이디", required = true)
                             @PathVariable("userId") String userId) {
         UserDTO dto = this.convertToDto(userService.findUser(userId));
         dto.setPassword(null);
         return dto;
    }

    @Operation(summary  = "사용자 리스트", description = "사용자 리스트를 가져온다.<br>querydsl custom, impl로 구현한 방식")
    @GetMapping("/list")
    public List<UserDTO> findUserList(UserDTO userDTO) {
        return userService.findUserList(userDTO);
    }

    @Operation(summary  = "사용자 리스트 2", description = "사용자 리스트를 가져온다.<br>querydsl 직접 구현한 방식")
    @GetMapping("/list2")
    public List<UserDTO> findUserList2(UserDTO userDTO) {
        return userService.findUserList2(userDTO);
    }

    @Operation(summary  = "사용자 생성", description = "사용자를 신규 등록한다.")
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return this.convertToDto(userService.createUser(this.convertToEntity(userDTO)));
    }

    @Operation(summary  = "실패 테스트", description = "읽기 전용으로 서비스 호출 후 쓰기가 필요한 서비스를 호출해본다.<br>실패하는 것이 정상이다.")
    @PostMapping("/test")
    public UserDTO failTest(@RequestBody UserDTO userDTO) {
        userService.findUserList(userDTO);
        return this.convertToDto(userService.createUser(this.convertToEntity(userDTO)));
    }

    @Operation(summary  = "사용자 수정", description = "사용자를 수정한다.")
    @PutMapping("{userSeq}")
    public UserDTO updateUser(@Parameter(description = "사용자 아이디", required = true)
                                  @PathVariable("userId") String userId,
                              @RequestBody UserDTO userDTO) {
        return this.convertToDto(userService.updateUser(userId, this.convertToEntity(userDTO)));
    }

    @Operation(hidden = true)
    @DeleteMapping("{userSeq}")
    public ResponseDTO deleteUser(@PathVariable("userSeq") Long userSeq) {
        return userService.deleteUser(userSeq);
    }

    // DTO 변환
    private UserDTO convertToDto(User user) {
        UserDTO dto = modelMapper.map(user, UserDTO.class);
        dto.setPassword(null);
        return dto;
    }

    // Entity 변환
    private User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        return user;
    }
}
