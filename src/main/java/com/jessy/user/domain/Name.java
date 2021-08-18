package com.jessy.user.domain;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    String firstName;
    String lastName;
}
