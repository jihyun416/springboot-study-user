package com.jessy.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jessy.user.util.CollectionUtil;
import com.jessy.user.web.dto.UserAuthorityDTO;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Audited
@AuditOverride(forClass=BaseEntity.class)
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class UserAuthority extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userAuthoritySeq;

    @NotAudited
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @NotAudited
    @ManyToOne
    @JoinColumn(name="authority_id")
    @JsonIgnore
    private Authority authority;

    public static UserAuthority fromUserAuthoritySeq(Long userAuthoritySeq) {
        if(!CollectionUtil.isEmpty(userAuthoritySeq)) return null;
        return UserAuthority.builder().userAuthoritySeq(userAuthoritySeq).build();
    }

    public UserAuthorityDTO toDTO() {
        UserAuthorityDTO dto = new UserAuthorityDTO();
        dto.setUserAuthoritySeq(this.userAuthoritySeq);
        if(!CollectionUtil.isEmpty(user)) dto.setUserId(this.user.getUserId());
        if(!CollectionUtil.isEmpty(authority)) dto.setAuthorityId(this.authority.getAuthorityId());
        return dto;
    }
}
