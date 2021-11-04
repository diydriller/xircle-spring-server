package com.project.xircle.repository;

import com.project.xircle.dto.interest.GetProfileDto;
import com.project.xircle.model.Interest;
import com.project.xircle.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestRepository extends CrudRepository<Interest,Long> {

    @Query("select new com.project.xircle.dto.interest.GetProfileDto(i.interest,count(i)) from Interest i  " +
            "where i.interest in (select i2.interest from Interest i2 where i2.user=:user)  " +
            "group by i.interest ")
    List<GetProfileDto> findSimilarInterest(@Param("user") User user);
}
