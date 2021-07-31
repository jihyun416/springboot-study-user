package com.jessy.user.web.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseDTO {
    private Boolean result; // 성공여부
    private Integer status; // HTTP 상태
    private String message; // 메시지
    private Object data; // 데이터
}