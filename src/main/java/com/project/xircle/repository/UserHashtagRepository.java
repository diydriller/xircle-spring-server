package com.project.xircle.repository;

import com.project.xircle.model.UserHashtag;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserHashtagRepository extends ReactiveCrudRepository<UserHashtag,Long> {
    Mono<UserHashtag> save(UserHashtag userHashtag);
}
