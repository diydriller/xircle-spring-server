package com.project.xircle.repository;

import com.project.xircle.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AuthRepository extends ReactiveCrudRepository<User,Long> {

    Mono<User> findByEmail(String email);
    Mono<User> findByDisplayName(String name);
    Mono<User> save(User user);
}
