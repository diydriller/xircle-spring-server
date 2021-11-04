package com.project.xircle.service;

import com.project.xircle.common.response.BaseResponse;
import com.project.xircle.common.util.AuthUtil;
import com.project.xircle.common.util.EntityDtoUtil;
import com.project.xircle.dto.post.CreatePostRequestDto;
import com.project.xircle.model.Hashtag;
import com.project.xircle.model.Post;
import com.project.xircle.model.User;
import com.project.xircle.model.UserHashtag;
import com.project.xircle.repository.HashtagRepository;
import com.project.xircle.repository.PostRepository;
import com.project.xircle.repository.UserHashtagRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

import static com.project.xircle.common.response.BaseResponseStatus.SUCCESS;

@Service
@RequiredArgsConstructor
public class PostService {

    @Value("${file.upload.path}")
    private String fileUploadPath;
    @Value("${file.down.path}")
    private String fileDownPath;

    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;
    private final UserHashtagRepository userHashtagRepository;

    public Mono<BaseResponse> createPost(CreatePostRequestDto requestDto
            , Mono<FilePart> postImgSrc, String token) {

        Path basePath = Paths.get(fileUploadPath);
        Post post = EntityDtoUtil.toPostEntity(requestDto);

        return Mono.just(token)
                .map(s->{
                    Long userId=AuthUtil.getId(s);
                    post.setUserId(userId);
                    return userId;
                })
                .then(postImgSrc
                    .flatMap(f->{
                        String currentDate = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
                        String imageExt = f.filename().split("\\.")[f.filename().split("\\.").length-1];
                        String filename="post"+currentDate+"."+imageExt;
                        post.setPostImgSrc(fileDownPath+filename);
                        return f.transferTo(basePath.resolve(filename));
                    })
                        .then(postRepository.save(post)
                                .flatMap(p->
                                    hashtagRepository.saveAll(
                                            requestDto.getHashtag()
                                                    .stream()
                                                    .map(s -> new Hashtag(s, p.getId()))
                                                    .collect(Collectors.toList())
                                    ).flatMap(h -> userHashtagRepository.save(new UserHashtag(p.getUserId(), h.getId())))
                                            .collectList()
                                )
                                .then(Mono.just(new BaseResponse(SUCCESS)))
                        ));
    }

}
