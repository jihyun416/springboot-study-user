package com.jessy.user.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jessy.user.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Schema(title = "아이디", description = "회원가입시 입력하는 아이디", example = "jessy")
    String userId;
    @Schema(title = "이름", example = "Jessy")
    String userName;
    @Schema(title = "비밀번호", example = "1234", accessMode = Schema.AccessMode.WRITE_ONLY)
    String password;

    public User toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(this, User.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(this.getPassword()));
        return user;
    }
}
