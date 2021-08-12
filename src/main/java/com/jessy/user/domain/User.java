package com.jessy.user.domain;

import com.jessy.user.web.dto.UserDTO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String userId;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    @Builder.Default
    private Integer attemptCount = 0;
    private String lastPassword;
    private LocalDateTime lastLoginDatetime;
    @Builder.Default
    private LocalDateTime passwordChangeDatetime = LocalDateTime.now();

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch= FetchType.LAZY, cascade= CascadeType.REMOVE, orphanRemoval = true)
    private List<UserAuthority> authorities = new ArrayList<>();

    public static User fromUserId(String userId) {
        if(userId==null) return null;
        else return User.builder().userId(userId).build();
    }

    public UserDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(this, UserDTO.class);
        userDTO.setPassword(null);
        return userDTO;
    }
}
