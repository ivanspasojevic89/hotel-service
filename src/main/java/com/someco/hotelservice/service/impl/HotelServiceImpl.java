package com.someco.hotelservice.service.impl;

import com.someco.hotelservice.api.request.CreateHotelRequest;
import com.someco.hotelservice.api.request.FindHotelRequest;
import com.someco.hotelservice.api.request.UpdateHotelRequest;
import com.someco.hotelservice.constant.CommonConstants;
import com.someco.hotelservice.dao.HotelDAO;
import com.someco.hotelservice.dao.ReviewDAO;
import com.someco.hotelservice.dao.UserDAO;
import com.someco.hotelservice.model.Hotel;
import com.someco.hotelservice.model.Review;
import com.someco.hotelservice.model.User;
import com.someco.hotelservice.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public Hotel createHotel(CreateHotelRequest createHotelRequest) throws Exception {
        Optional<User> user = userDAO.findById(createHotelRequest.getUserID());
        if (!user.isPresent()) {
            throw new Exception("Invalid userID");
        }
        Hotel hotel = Hotel.builder()
                .hotelAddress(createHotelRequest.getAddress())
                .hotelDescription(createHotelRequest.getDescription())
                .hotelImage(createHotelRequest.getHotelImage())
                .hotelLat(createHotelRequest.getLatitude())
                .hotelLon(createHotelRequest.getLongitude())
                .hotelName(createHotelRequest.getName())
                .user(user.get())
                .build();

        hotelDAO.save(hotel);
        return hotel;
    }

    @Override
    public Optional<Hotel> findByID(Long hotelID) {
        return hotelDAO.findById(hotelID);
    }

    @Override
    public Hotel updateHotel(UpdateHotelRequest updateHotelRequest) throws Exception {
        Optional<Hotel> hotelFromDB = hotelDAO.findById(updateHotelRequest.getHotelID());
        if (!hotelFromDB.isPresent()) {
            throw new Exception("Hotel not found for requested HotelID");
        }
        Hotel hotel = hotelFromDB.get();
        hotel.setHotelAddress(updateHotelRequest.getAddress());
        hotel.setHotelDescription(updateHotelRequest.getDescription());
        hotel.setHotelName(updateHotelRequest.getName());
        hotel.setHotelLat(updateHotelRequest.getLatitude());
        hotel.setHotelLon(updateHotelRequest.getLongitude());
        hotel.setHotelImage(updateHotelRequest.getHotelImage());

        hotelDAO.save(hotel);
        return hotel;
    }

    @Override
    public List<Hotel> findHotelsByCriteria(FindHotelRequest findHotelRequest) {
        int limit = findHotelRequest.getLimit() > CommonConstants.MAX_HOTEL_LIST_LIMIT ? CommonConstants.MAX_HOTEL_LIST_LIMIT : findHotelRequest.getLimit();
        findHotelRequest.setLimit(limit);

        return hotelDAO.findHotelsByCriteria(findHotelRequest, "hotelName", "ASC");
    }

    @Override
    public Long getTotalCount(FindHotelRequest findHotelRequest) {
        return hotelDAO.getTotalSize(findHotelRequest);
    }

    @Override
    public List<Hotel> getHotelList(Integer limit) {
        limit = limit > CommonConstants.MAX_HOTEL_LIST_LIMIT ? CommonConstants.MAX_HOTEL_LIST_LIMIT : limit;

        return hotelDAO.findAll(limit);
    }

    @Override
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void calculateOverallRate(Long hotelID) {

        Optional<Hotel> hotelFromDB = hotelDAO.findLockedByID(hotelID);
        if (!hotelFromDB.isPresent()) {
            return;
        }

        List<Review> reviews = reviewDAO.findAllByHotelID(hotelID);
        Double averageRate = reviews.stream().mapToDouble(Review::getCalculatedRate).average().orElse(Double.NaN);

        DecimalFormat df = new DecimalFormat("#.##");
        Hotel hotel = hotelFromDB.get();
        hotel.setHotelOverallRating(new BigDecimal(df.format(averageRate)));

    }
}
