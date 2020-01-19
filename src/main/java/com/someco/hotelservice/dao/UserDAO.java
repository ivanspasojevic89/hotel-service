package com.someco.hotelservice.dao;

import com.someco.hotelservice.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDAO extends CrudRepository<User, Long> {

    @Query(value = "select * from user where UserID in " +
            "(select ReviewConfirmationUserID from review_confirmation " +
            "where ReviewConfirmationReviewID = :reviewID " +
            "and ReviewConfirmationType = :reviewType)", nativeQuery = true)
    List<User> findUsersInReviewConfirmation(@Param("reviewID") Long reviewID, @Param("reviewType") String reviewType);

}
