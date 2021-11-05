package com.project.xircle.service;

import com.project.xircle.dto.post.CreatePostRequestDto;
import com.project.xircle.dto.post.GetPostResponseDto;
import com.project.xircle.dto.post.GetProfilePostResponseDto;
import com.project.xircle.error.BaseException;
import com.project.xircle.model.Hashtag;
import com.project.xircle.model.Post;
import com.project.xircle.model.User;
import com.project.xircle.repository.PostRepository;
import com.project.xircle.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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



    public List<GetPostResponseDto> getPost(Long userId,int page){
        PageRequest pageRequest = PageRequest.of(page,8, Sort.Direction.DESC, "createdTime");
        return postRepository.findPostByUserId(userId,pageRequest)
                .stream()
                .map(p->{
                    GetPostResponseDto responseDto=new GetPostResponseDto();
                    BeanUtils.copyProperties(p,responseDto);
                    responseDto.setHashtag(p.getHashtagList().stream().map(h->h.getHashtag()).collect(Collectors.toList()));
                    String current_date = new SimpleDateFormat("YY/MM/dd HH:mm").format(Timestamp.valueOf(p.getCreatedTime()));
                    responseDto.setCreatedAt(current_date);
                    return responseDto;
                }).collect(Collectors.toList());
    }

    public GetProfilePostResponseDto getProfilePost(Long userId,String interest,int page) {
        PageRequest pageRequest = PageRequest.of(page,8, Sort.Direction.DESC, "createdTime");
        List<GetProfilePostResponseDto.GetProfilePostDto> profilePostDtoList =
                postRepository.findPostByUserIdAndInterest(userId,interest,pageRequest)
                .stream()
                .map(p->{
                    GetProfilePostResponseDto.GetProfilePostDto dto=new GetProfilePostResponseDto.GetProfilePostDto();
                    BeanUtils.copyProperties(p,dto);
                    return dto;
                }).collect(Collectors.toList());
        GetProfilePostResponseDto responseDto=new GetProfilePostResponseDto();
        responseDto.setUserId(userId);
        responseDto.setPostList(profilePostDtoList);
        return responseDto;
    }

    // 이미지 업로드 함수
    String imageUpload(MultipartFile imageFile) throws BaseException, IOException {

        String current_date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

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
