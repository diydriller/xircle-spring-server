package com.project.xircle.repository;

import com.project.xircle.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByDisplayName(String name);

    @Query("select u from Users u " +
            "where u.id<>:userId order by random() ")
    List<User> findRandomUserById(@Param("userId") Long userId, Pageable pageable);

    @Query("select u from Users u "+
            "where u.id=:userId ")
    List<User> findUserById(@Param("userId") Long userId, Pageable pageRequest);
}
