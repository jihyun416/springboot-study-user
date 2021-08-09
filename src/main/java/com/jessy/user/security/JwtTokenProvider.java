package com.jessy.user.security;

import com.jessy.user.exception.InvalidAccessTokenException;
import com.jessy.user.exception.InvalidRefreshTokenException;
import com.jessy.user.util.CollectionUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@NoArgsConstructor
public class JwtTokenProvider { // JWT 토큰을 생성 및 검증 모듈
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.access.name}")
    public String ACCESS_TOKEN;
    @Value("${jwt.refresh.name}")
    public String REFRESH_TOKEN;
    @Value("${jwt.access.valid}")
    public Long ACCESS_VALID;
    @Value("${jwt.refresh.valid}")
    public Long REFRESH_VALID;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // Access Token 생성
    public String createAccessToken(String subject, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("type", ACCESS_TOKEN);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + ACCESS_VALID)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret값 세팅
                .compact();
    }

    // Refresh Token 생성
    public String createRefreshToken(String subject, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("type", REFRESH_TOKEN);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 데이터
                .setIssuedAt(now) // 토큰 발행일자
                .setExpiration(new Date(now.getTime() + REFRESH_VALID)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey) // 암호화 알고리즘, secret값 세팅
                .compact();
    }

    // Request의 Header에서 token 가져오기
    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(!CollectionUtil.isEmpty(bearer)) return bearer.replace("Bearer ", "");
        else return null;
    }

    // Jwt 토큰으로 인증 정보 반환
    public Authentication getAuthentication(String token) {
        String userId = this.getSubject(token);
        List<String> roles = this.getRoles(token);
        UserDetails userDetails = new SecurityUser(userId, roles);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // subject 추출 (userId)
    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // role 추출 (권한 리스트)
    public List<String> getRoles(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("roles", ArrayList.class);
    }

    // Access token 유효성 체크
    public void validateAccessToken(String token) throws Exception {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        if(!claims.getBody().get("type", String.class).equals(ACCESS_TOKEN)) {
            throw new InvalidAccessTokenException("Access Token이 아닙니다.");
        }
    }

    // Refresh token 유효성 체크
    public void validateRefreshToken(String token) throws Exception {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        if(!claims.getBody().get("type", String.class).equals(REFRESH_TOKEN)) {
            throw new InvalidRefreshTokenException("Refresh Token이 아닙니다.");
        }
    }

    // Jwt 토큰 만료 정보
    public String getExp(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return String.valueOf(claims.getBody().getExpiration().getTime());
    }
}