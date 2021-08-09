package com.jessy.user.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jessy.user.domain.Authority;
import com.jessy.user.domain.User;
import com.jessy.user.domain.UserAuthority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserAuthorityDTO {
    @Schema(title = "사용자 권한 일련번호", example = "1")
    private Long userAuthoritySeq;
    @Schema(title = "사용자 아이디", example = "jessy")
    private String userId;
    @Schema(title = "권한 아이디", example = "ADMIN")
    private String authorityId;

    public UserAuthority toEntity() {
        UserAuthority entity = new UserAuthority();
        if(this.userAuthoritySeq!=null) entity.setUserAuthoritySeq(this.userAuthoritySeq);
        if(this.userId!=null) entity.setUser(User.fromUserId(this.userId));
        if(this.authorityId!=null) entity.setAuthority(Authority.fromAuthorityId(this.authorityId));
        return entity;
    }
}
