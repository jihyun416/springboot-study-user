package com.jessy.user.web.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    Long userSeq;
    String userId;
    String userName;
}
