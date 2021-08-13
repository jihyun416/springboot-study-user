package com.jessy.user.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserAuthoritySetDTO {
    List<UserAuthorityDTOForAdd> add;
    List<UserAuthorityDTOForDelete> delete;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class UserAuthorityDTOForAdd {
        @Schema(title = "권한 아이디", example="ADMIN")
        private String authorityId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class UserAuthorityDTOForDelete {
        @Schema(title = "사용자 권한 일련번호", example="1")
        private Long userAuthoritySeq;
    }
}
