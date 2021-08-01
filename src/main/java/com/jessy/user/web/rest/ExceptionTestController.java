package com.jessy.user.web.rest;

import com.jessy.user.exception.AccessDeniedException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RequestMapping("/exception")
@RestController
public class ExceptionTestController {
    @GetMapping("/runtime")
    public String exceptionTest500() {
        throw new RuntimeException();
    }

    @GetMapping("/noauth")
    public String exceptionTest403() throws AccessDeniedException {
        throw new AccessDeniedException("Access Denied.");
    }
}
