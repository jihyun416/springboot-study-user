package com.jessy.user.aop;

import com.jessy.user.exception.AccessDeniedException;
import com.jessy.user.exception.AuthenticationEntryPointException;
import com.jessy.user.exception.NoAuthenticationException;
import com.jessy.user.web.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@EnableWebMvc
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(NoAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDTO NoAuthenticationExceptionException(NoAuthenticationException e) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .result(false)
                .message(e.getClass().getName())
                .data(e.getMessage())
                .build();
        return responseDTO;
    }

    @ExceptionHandler(value={AccessDeniedException.class, AuthenticationEntryPointException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseDTO AuthenticationEntryPointException(AccessDeniedException e) {
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .result(false)
                .message(e.getClass().getName())
                .data(e.getMessage())
                .build();
        return responseDTO;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDTO noHandlerFoundException(NoHandlerFoundException e) {
        log.error(e.getMessage());
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .result(false)
                .message(e.getClass().getName())
                .data(e.getMessage())
                .build();
        return responseDTO;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDTO handleException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String sStackTrace = sw.toString();
        log.error(sStackTrace);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .result(false)
                .message(e.getClass().getName())
                .data(sStackTrace)
                .build();
        return responseDTO;
    }
}