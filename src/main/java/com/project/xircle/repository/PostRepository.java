package com.project.xircle.repository;

import com.project.xircle.model.Post;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface PostRepository extends ReactiveCrudRepository<Post,Long> {
    Mono<Post> save(Post post);
}
