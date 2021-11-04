package com.project.xircle.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;
    private String content;
    private String title;
    @Column(name="post_img_src")
    private String postImgSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Hashtag> hashtagList=new ArrayList<>();

   void setUser(User user){
       this.user=user;
       user.getPostList().add(this);
   }

   void addHashtag(Hashtag hashtag){
       hashtagList.add(hashtag);
       hashtag.setPost(this);
   }

   public static Post createPost(String content,String title,String postImgSrc,User user,List<Hashtag> hashtags){
        Post post = new Post();
        post.setPostImgSrc(postImgSrc);
        post.setUser(user);
        post.setContent(content);
        post.setTitle(title);
        for(Hashtag hashtag:hashtags){
            post.addHashtag(hashtag);
        }
        return post;
   }

}
