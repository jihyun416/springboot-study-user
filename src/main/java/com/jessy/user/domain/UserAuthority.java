package com.jessy.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jessy.user.util.CollectionUtil;
import com.jessy.user.web.dto.UserAuthorityDTO;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

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

    @Audited(targetAuditMode = NOT_AUDITED)
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @Audited(targetAuditMode = NOT_AUDITED)
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
