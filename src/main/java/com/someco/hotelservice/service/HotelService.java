package com.someco.hotelservice.service;

import com.someco.hotelservice.api.request.CreateHotelRequest;
import com.someco.hotelservice.api.request.FindHotelRequest;
import com.someco.hotelservice.api.request.UpdateHotelRequest;
import com.someco.hotelservice.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    Hotel createHotel(CreateHotelRequest createHotelRequest) throws Exception;

    Optional<Hotel> findByID(Long hotelID);

    Hotel updateHotel(UpdateHotelRequest updateHotelRequest) throws Exception;

    List<Hotel> findHotelsByCriteria(FindHotelRequest findHotelRequest);

    Long getTotalCount(FindHotelRequest findHotelRequest);

    List<Hotel> getHotelList(Integer limit);

    void calculateOverallRate(Long hotelID);
}
