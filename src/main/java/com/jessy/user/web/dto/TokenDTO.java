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
public class TokenDTO {
    @Schema(title = "결과", example = "true")
    private Boolean result;

    @Schema(title = "메시지", example = "로그인에 성공했습니다.")
    private String message;

    @Schema(title = "엑세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXNzeSIsInR5cGUiOiJhY2Nlc3MiLCJyb2xlcyI6WyJBRE1JTiJdLCJpYXQiOjE2Mjg0MzU0NzEsImV4cCI6MTYyODQzOTA3MX0.rPfuEq3CWT0sqvlu5MDaZKkyWqRJv7VRBU6QgpElh3o")
    private String accessToken;

    @Schema(title = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXNzeSIsInR5cGUiOiJhY2Nlc3MiLCJyb2xlcyI6WyJBRE1JTiJdLCJpYXQiOjE2Mjg0MzU0NzEsImV4cCI6MTYyODQzOTA3MX0.rPfuEq3CWT0sqvlu5MDaZKkyWqRJv7VRBU6QgpElh3o")
    private String refreshToken;

    @Schema(title = "엑세스 토큰 만료시간", example = "1628439071000")
    private String accessTokenExp;

    @Schema(title = "리프레시 토큰 만료시간", example = "1628439071000")
    private String refreshTokenExp;
}