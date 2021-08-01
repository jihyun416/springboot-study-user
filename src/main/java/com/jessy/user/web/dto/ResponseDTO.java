package com.jessy.user.web.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title="성공여부", example="true")
    private Boolean result; // 성공여부
    @Schema(title="HTTP 상태", example="200")
    private Integer status; // HTTP 상태
    @Schema(title="메시지", example="Deleted Successfully!")
    private String message; // 메시지
    @Schema(title="데이터", example="{userSeq:1}")
    private Object data; // 데이터
}