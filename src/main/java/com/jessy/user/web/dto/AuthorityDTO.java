package com.jessy.user.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jessy.user.domain.Authority;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthorityDTO {
    @Schema(title = "권한 아이디", example="ADMIN")
    private String authorityId;
    @Schema(title = "권한명", example="관리자")
    private String authorityName;
    @Schema(title = "권한 설명", example="관리자")
    private String authorityDescription;

    public Authority toEntity() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Authority entity = modelMapper.map(this, Authority.class);
        return entity;
    }
}
