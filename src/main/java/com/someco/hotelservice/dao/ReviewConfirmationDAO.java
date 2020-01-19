package com.someco.hotelservice.dao;

import com.someco.hotelservice.model.ReviewConfirmation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ReviewConfirmationDAO extends CrudRepository<ReviewConfirmation, Long> {
    @Query(value = "select * from review_confirmation where ReviewConfirmationReviewID =:reviewID and ReviewConfirmationUserID = :userID and ReviewConfirmationType = :confirmationType", nativeQuery = true)
    Optional<ReviewConfirmation> findByUserAndReview(@Param("reviewID") Long reviewID, @Param("userID") Long userID, @Param("confirmationType") String confirmationType);
}
