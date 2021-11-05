package com.project.xircle.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetProfilePostResponseDto {

    private Long userId;
    private List<GetProfilePostDto> postList;

    @Data
    @NoArgsConstructor
    public static class GetProfilePostDto{
        private String postImgSrc;
        private String title;
    }
}
