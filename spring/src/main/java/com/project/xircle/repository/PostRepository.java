package com.project.xircle.repository;

import com.project.xircle.model.Post;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends CrudRepository<Post,Long> {

    @Query("select p from Post p join p.user u " +
            "where u.id=:userId ")
    List<Post> findPostByUserId(@Param("userId") Long userId, Pageable pageable);

    @Query("select distinct p from Post p join p.user u join p.hashtagList h " +
            "where u.id=:userId and h.hashtag like concat('%',:interest,'%') ")
    List<Post> findPostByUserIdAndInterest(@Param("userId") Long userId,
                                     @Param("interest") String interest,
                                     Pageable pageable);
}
