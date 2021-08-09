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
public class SignInDTO {
    @Schema(title = "아이디", example="jessy")
    private String userId;
    @Schema(title = "비밀번호", example="12345678")
    private String password;
}
