package com.project.xircle.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePostRequestDto {
    @NotBlank(message = "title is empty")
    private String title;
    @NotBlank(message = "content is empty")
    private String content;
    @NotNull(message = "hashtag is empty")
    private List<String> hashtag;
}
