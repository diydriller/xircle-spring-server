package com.project.xircle.repository;

import com.project.xircle.model.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PostRepository extends ReactiveCrudRepository<Post,Long> {
}
