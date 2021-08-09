package com.jessy.user.web.rest;

import com.jessy.user.service.SignService;
import com.jessy.user.web.dto.ResponseDTO;
import com.jessy.user.web.dto.SignInDTO;
import com.jessy.user.web.dto.SignUpDTO;
import com.jessy.user.web.dto.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Tag(name="01. Sign", description = "로그인/회원가입")
@RequiredArgsConstructor
@RequestMapping("/sign")
@RestController
public class SignController {
    private final SignService signService;

    @Operation(summary  = "회원 가입", description = "회원 가입을 한다.")
    @PostMapping("/up")
    public ResponseDTO signUp(@RequestBody SignUpDTO signUpDTO) {
        return signService.signUp(signUpDTO);
    }

    @Operation(summary  = "로그인", description = "로그인을 한다.")
    @PostMapping("/in")
    public TokenDTO signIn(@RequestBody SignInDTO signInDTO) {
        return signService.signIn(signInDTO);
    }

    @Operation(summary = "토큰 재발급", description = "토큰을 재발급 받는다.<br><br>" +
            "정상[200]<br>" +
            "유효기간지남[401] -> io.jsonwebtoken.ExpiredJwtException<br>" +
            "잘못된형식[401] -> io.jsonwebtoken.MalformedJwtException<br>" +
            "토큰변조[401] -> io.jsonwebtoken.SignatureException<br>" +
            "refreshToken 아님/없음[401] -> com.bcm.exception.InvalidRefreshTokenException")
    @PostMapping(value="/refreshToken")
    public TokenDTO refreshToken(HttpServletRequest request) throws Exception {
        return signService.refreshToken(request);
    }
}
