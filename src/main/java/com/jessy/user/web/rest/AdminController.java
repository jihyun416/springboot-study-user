package com.jessy.user.web.rest;

import com.jessy.user.web.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="03. Admin", description = "관리자")
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Operation(summary  = "테스트", description = "테스트")
    @GetMapping("/test")
    public ResponseDTO test() {
        return ResponseDTO.builder().result(true).status(HttpStatus.OK.value()).build();
    }
}
