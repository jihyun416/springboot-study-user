package com.jessy.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jessy.user.domain.Authority;
import com.jessy.user.domain.User;
import com.jessy.user.domain.UserAuthority;
import com.jessy.user.exception.InvalidRefreshTokenException;
import com.jessy.user.repository.AuthorityRepository;
import com.jessy.user.repository.UserAuthorityRepository;
import com.jessy.user.repository.UserRepository;
import com.jessy.user.security.JwtTokenProvider;
import com.jessy.user.util.AESUtil;
import com.jessy.user.util.CollectionUtil;
import com.jessy.user.web.dto.ResponseDTO;
import com.jessy.user.web.dto.SignInDTO;
import com.jessy.user.web.dto.SignUpDTO;
import com.jessy.user.web.dto.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SignService {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final AuthorityRepository authorityRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public ResponseDTO signUp(SignUpDTO signUpDTO) {
        Optional<User> mightUser = userRepository.findByUserIdEquals(signUpDTO.getUserId());
        if(mightUser.isPresent()) {
            return ResponseDTO.builder()
                    .status(HttpStatus.OK.value())
                    .result(false)
                    .message("이미 가입된 아이디 입니다.")
                    .build();
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User savedUser = userRepository.save(User.builder()
                    .userId(signUpDTO.getUserId())
                    .userName(signUpDTO.getUserName())
                    .email(AESUtil.encrypt(signUpDTO.getEmail()))
                    .phoneNumber(AESUtil.encrypt(signUpDTO.getPhoneNumber()))
                    .password(passwordEncoder.encode(signUpDTO.getPassword()))
                    .lastLoginDatetime(LocalDateTime.now())
                    .passwordChangeDatetime(LocalDateTime.now())
                    .attemptCount(0)
                    .build());
            if(signUpDTO.getAuthority()!=null && !signUpDTO.getAuthority().isEmpty()) {
                Optional<Authority> mightAuthority = authorityRepository.findById(signUpDTO.getAuthority());
                mightAuthority.ifPresent(auth -> {
                    userAuthorityRepository.save(UserAuthority.builder().authority(auth).user(savedUser).build());
                });
            }
            return ResponseDTO.builder()
                    .status(HttpStatus.OK.value())
                    .result(true)
                    .message("가입이 완료되었습니다.")
                    .build();
        }
    }

    @Transactional(readOnly = true)
    public TokenDTO signIn(SignInDTO signInDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<User> mightUser = userRepository.findByUserIdEquals(signInDTO.getUserId());
        if(mightUser.isPresent()) {
            User user = mightUser.get();
            if(passwordEncoder.matches(signInDTO.getPassword(), user.getPassword())) {
                ArrayList<String> roles = new ArrayList<>();
                for (UserAuthority auth : user.getAuthorities()) {
                    roles.add(auth.getAuthority().getAuthorityId());
                }
                //jwt 발급
                String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), roles);
                String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId(), roles);
                return TokenDTO.builder()
                        .result(true)
                        .message("로그인에 성공했습니다.")
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .accessTokenExp(jwtTokenProvider.getExp(accessToken))
                        .refreshTokenExp(jwtTokenProvider.getExp(refreshToken))
                        .build();
            } else {
                return TokenDTO.builder().result(false).message("가입하지 않은 이메일이거나, 잘못된 비밀번호입니다.").build();
            }
        } else {
            return TokenDTO.builder().result(false).message("가입하지 않은 이메일이거나, 잘못된 비밀번호입니다.").build();
        }
    }

    @Transactional(readOnly = true)
    public TokenDTO refreshToken(HttpServletRequest request) throws Exception {
        String token = jwtTokenProvider.resolveToken(request);
        if(!CollectionUtil.isEmpty(token)) {
            jwtTokenProvider.validateRefreshToken(token);
            Optional<User> mightUser = userRepository.findByUserIdEquals(jwtTokenProvider.getSubject(token));
            if(mightUser.isPresent()) {
                User user = mightUser.get();
                ArrayList<String> roles = new ArrayList<>();
                for (UserAuthority auth : user.getAuthorities()) {
                    roles.add(auth.getAuthority().getAuthorityId());
                }
                //jwt 발급
                String accessToken = jwtTokenProvider.createAccessToken(user.getUserId(), roles);
                String refreshToken = jwtTokenProvider.createRefreshToken(user.getUserId(), roles);
                return TokenDTO.builder()
                        .result(true)
                        .message("로그인에 성공했습니다.")
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .accessTokenExp(jwtTokenProvider.getExp(accessToken))
                        .refreshTokenExp(jwtTokenProvider.getExp(refreshToken))
                        .build();
            } else {
                throw new InvalidRefreshTokenException("유효하지 않은 아이디 입니다.");
            }
        } else {
            throw new InvalidRefreshTokenException("Refresh Token이 필요합니다.");
        }
    }
}
