package com.project.xircle.common.util;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.project.xircle.dto.auth.CreateUserRequestDto;
import com.project.xircle.dto.post.CreatePostRequestDto;
import com.project.xircle.model.Hashtag;
import com.project.xircle.model.Post;
import com.project.xircle.model.User;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static User toUserEntity(CreateUserRequestDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto,user);
        return user;
    }

    public static Post toPostEntity(CreatePostRequestDto dto){
        Post post = new Post();
        BeanUtils.copyProperties(dto,post);
        return post;
    }

}
