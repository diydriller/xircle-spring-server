package com.project.xircle.repository;

import com.project.xircle.dto.user.GetUserCondition;
import com.project.xircle.dto.user.GetUserResponseDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<GetUserResponseDto> findUserByCondition(GetUserCondition condition);
}
