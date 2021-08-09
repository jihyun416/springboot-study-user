package com.jessy.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jessy.user.util.CollectionUtil;
import com.jessy.user.web.dto.UserAuthorityDTO;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserAuthority {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userAuthoritySeq;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name="authority_id")
    @JsonIgnore
    private Authority authority;

    public UserAuthorityDTO toDTO() {
        UserAuthorityDTO dto = new UserAuthorityDTO();
        dto.setUserAuthoritySeq(this.userAuthoritySeq);
        if(!CollectionUtil.isEmpty(user)) dto.setUserId(this.user.getUserId());
        if(!CollectionUtil.isEmpty(authority)) dto.setAuthorityId(this.authority.getAuthorityId());
        return dto;
    }
}
