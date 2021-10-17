package com.project.xircle.common.util;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.project.xircle.dto.auth.CreateUserRequestDto;
import com.project.xircle.model.User;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static User toEntity(CreateUserRequestDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return user;
    }
}
