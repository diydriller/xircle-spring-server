package com.project.xircle.repository;

import com.project.xircle.dto.user.GetUserCondition;
import com.project.xircle.dto.user.GetUserResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mysema.commons.lang.Assert.hasText;
import static com.project.xircle.model.QUser.user;


public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<GetUserResponseDto> findUserByCondition(GetUserCondition condition){
        return queryFactory
                .select(Projections.bean(GetUserResponseDto.class,user.id,user.displayName))
                .from(user)
                .where(
                        ageEq(condition.getAge()),
                        universityEq(condition.getUniversity()),
                        genderEq(condition.getGender())
                ).fetch();
    }

    private BooleanExpression ageEq(Integer age){
        return age!=null?user.age.eq(age):null;
    }
    private BooleanExpression universityEq(String university){
        return university!=null?user.university.eq(university):null;
    }
    private BooleanExpression genderEq(String gender){
        return gender!=null?user.gender.eq(gender):null;
    }


}
