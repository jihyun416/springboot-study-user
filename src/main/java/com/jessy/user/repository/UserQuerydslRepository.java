package com.jessy.user.repository;

import com.jessy.user.web.dto.UserDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.jessy.user.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public List<UserDTO> findUserList(UserDTO userDTO) {
        return queryFactory
                .select(Projections.bean(UserDTO.class,
                        user.userSeq,
                        user.userId,
                        user.userName))
                .from(user)
                .where(
                        containsUserId(userDTO.getUserId()),
                        containsUserName(userDTO.getUserName())
                )
                .fetch()
                ;
    }

    BooleanExpression containsUserId(String userId) {
        if(userId==null || userId.isEmpty())
            return null;
        return user.userId.contains(userId);
    }

    BooleanExpression containsUserName(String userName) {
        if(userName==null || userName.isEmpty())
            return null;
        return user.userName.containsIgnoreCase(userName);
    }
}
