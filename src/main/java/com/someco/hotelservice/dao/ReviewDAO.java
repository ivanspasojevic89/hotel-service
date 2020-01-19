package com.someco.hotelservice.dao;

import com.someco.hotelservice.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewDAO extends CrudRepository<Review, Long> {
    @Query(value = "select * from review where ReviewHotelID = :hotelID", nativeQuery = true)
    List<Review> findAllByHotelID(@Param("hotelID") Long hotelID);
}
