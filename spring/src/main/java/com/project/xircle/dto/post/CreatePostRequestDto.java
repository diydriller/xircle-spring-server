package com.project.xircle.dto.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CreatePostRequestDto {

    @NotEmpty(message="content is empty")
    private String content;

    private List<String> hashtagArr=new ArrayList<>();
    @NotEmpty(message="title is empty")
    private String title;

    @NotNull(message="articleImg is empty")
    private MultipartFile articleImg;
}
