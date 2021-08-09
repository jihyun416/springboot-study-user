package com.jessy.user.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SignUpDTO {
    @Schema(title = "사용자 아이디", example = "jessy")
    private String userId;
    @Schema(title = "사용자 명", example = "제시")
    private String userName;
    @Schema(title = "비밀번호", example = "12345678")
    private String password;
    @Schema(title = "이메일", example = "jessy@jessy.com")
    private String email;
    @Schema(title = "휴대폰번호", example = "01000000000")
    private String phoneNumber;
    @Schema(title = "권한", example = "ADMIN")
    private String authority;
}
