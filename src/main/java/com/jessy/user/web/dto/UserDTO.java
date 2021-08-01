package com.jessy.user.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @Schema(title="사용자 일련번호", description = "자동생성", example="1", accessMode = Schema.AccessMode.READ_ONLY)
    Long userSeq;
    @Schema(title="아이디", description = "회원가입시 입력하는 아이디", example="jessy")
    String userId;
    @Schema(title="이름", example="Jessy")
    String userName;
    @Schema(title="비밀번호", example="1234", accessMode = Schema.AccessMode.WRITE_ONLY)
    String password;
}
