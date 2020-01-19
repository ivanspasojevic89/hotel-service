package com.someco.hotelservice.dao;

import com.someco.hotelservice.dao.impl.HotelFacade;
import com.someco.hotelservice.model.Hotel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface HotelDAO extends CrudRepository<Hotel, Long>, HotelFacade {

    @Query(value = "select * from hotel limit :limit", nativeQuery = true)
    List<Hotel> findAll(@Param("limit") int limit);

    @Query(value = "select * from hotel where HotelID = :hotelID for update", nativeQuery = true)
    Optional<Hotel> findLockedByID(@Param("hotelID") Long hotelID);
}

