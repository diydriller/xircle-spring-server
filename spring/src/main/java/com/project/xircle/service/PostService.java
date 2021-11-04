package com.project.xircle.service;

import com.project.xircle.dto.post.CreatePostRequestDto;
import com.project.xircle.error.BaseException;
import com.project.xircle.model.Hashtag;
import com.project.xircle.model.Post;
import com.project.xircle.model.User;
import com.project.xircle.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${file.upload.path}")
    private String fileUploadPath;
    @Value("${file.down.path}")
    private String fileDownPath;


    private final PostRepository postRepository;

    public void createPost(User user, CreatePostRequestDto requestDto) throws IOException {
        List<Hashtag> hashtagList=requestDto.getHashtagArr()
                .stream().map(h->Hashtag.createHashtag(h))
                .collect(Collectors.toList());
        String postImg=imageUpload(requestDto.getArticleImg());
        Post post=Post.createPost(requestDto.getContent(),requestDto.getTitle(),postImg,user,hashtagList);
        postRepository.save(post);
    }

    // 이미지 업로드 함수
    String imageUpload(MultipartFile imageFile) throws BaseException, IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String current_date = simpleDateFormat.format(new Date());

        String basePath = new File("").getAbsolutePath()+fileUploadPath;


        String[] imageFileFlags = imageFile.getOriginalFilename().split("\\.");
        String imageExt=imageFileFlags[imageFileFlags.length-1];

        String imagePath = basePath + "post" + current_date + "." + imageExt;
        String imageDownUrl = fileDownPath + "post" + current_date + "." + imageExt;

        File dest = new File(imagePath);
        imageFile.transferTo(dest);

        return imageDownUrl;
    }
}
