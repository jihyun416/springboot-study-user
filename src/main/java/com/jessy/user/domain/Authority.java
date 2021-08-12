package com.jessy.user.domain;

import com.jessy.user.util.CollectionUtil;
import com.jessy.user.web.dto.AuthorityDTO;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Authority extends BaseEntity {
    @Id
    private String authorityId;
    private String authorityName;
    private String authorityDescription;

    @Builder.Default
    @OneToMany(mappedBy = "authority", fetch= FetchType.LAZY, cascade= CascadeType.REMOVE, orphanRemoval = true)
    private List<UserAuthority> userAuthorities = new ArrayList<>();

    public static Authority fromAuthorityId(String authorityId) {
        if(CollectionUtil.isEmpty(authorityId)) return null;
        else return Authority.builder().authorityId(authorityId).build();
    }

    public AuthorityDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        AuthorityDTO dto = modelMapper.map(this, AuthorityDTO.class);
        return dto;
    }
}
