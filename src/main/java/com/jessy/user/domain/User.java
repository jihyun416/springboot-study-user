package com.jessy.user.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Table
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long userSeq;
    private String userId;
    private String userName;
    private String password;
}
