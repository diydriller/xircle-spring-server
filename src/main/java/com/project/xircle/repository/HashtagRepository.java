package com.project.xircle.repository;

import com.project.xircle.model.Hashtag;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface HashtagRepository extends ReactiveCrudRepository<Hashtag,Long> {

    Flux<Hashtag> saveAll(List<Hashtag> hashtagList);

}
