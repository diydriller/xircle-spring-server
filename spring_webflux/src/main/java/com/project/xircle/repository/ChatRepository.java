package com.project.xircle.repository;

import com.project.xircle.model.Chat;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ChatRepository extends ReactiveCrudRepository<Chat,Long> {
}
