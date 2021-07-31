package com.jessy.user.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {
    @RequestMapping
    public String hello() {
        return "Hello!";
    }
}
