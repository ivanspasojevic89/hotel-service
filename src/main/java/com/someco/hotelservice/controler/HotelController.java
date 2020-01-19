package com.someco.hotelservice.controler;

import com.someco.hotelservice.api.request.FindHotelRequest;
import com.someco.hotelservice.api.response.FindHotelResponse;
import com.someco.hotelservice.api.response.HotelInfoResponse;
import com.someco.hotelservice.constant.CommonConstants;
import com.someco.hotelservice.dto.HotelDTO;
import com.someco.hotelservice.model.Hotel;
import com.someco.hotelservice.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(path = "/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;


    @RequestMapping(path = "/getHotelInfo", method = RequestMethod.GET)
    public HotelInfoResponse getHotelInfo(@RequestParam Long hotelID) {
        HotelInfoResponse hotelInfoResponse = new HotelInfoResponse();
        try {
            Optional<Hotel> hotel = hotelService.findByID(hotelID);
            if (!hotel.isPresent()) {
                hotelInfoResponse.setResultCode(CommonConstants.HOTEL_NOT_FOUND);
                hotelInfoResponse.setResponseInfo("Hotel not found for requested HotelID");
                return hotelInfoResponse;
            }
            hotelInfoResponse.setHotel(new HotelDTO(hotel.get()));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            hotelInfoResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            hotelInfoResponse.setResponseInfo(ex.getMessage());
        }

        return hotelInfoResponse;
    }


    @RequestMapping(path = "/findHotels", method = RequestMethod.POST)
    public FindHotelResponse findHotels(@RequestBody FindHotelRequest findHotelRequest) {
        FindHotelResponse findHotelResponse = new FindHotelResponse();
        try {
            List<Hotel> hotels = hotelService.findHotelsByCriteria(findHotelRequest);
            List<HotelDTO> hotelDTOS = new LinkedList<>();
            hotels.forEach(hotel -> {
                hotelDTOS.add(new HotelDTO(hotel));
            });
            findHotelResponse.setHotels(hotelDTOS);
            Long totalCount = hotelService.getTotalCount(findHotelRequest);
            findHotelResponse.setTotalCount(totalCount);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            findHotelResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            findHotelResponse.setResponseInfo(ex.getMessage());
        }
        return findHotelResponse;
    }

    @RequestMapping(path = "/getHotelList", method = RequestMethod.GET)
    public FindHotelResponse getHotelList(@RequestParam Integer limit) {
        FindHotelResponse findHotelResponse = new FindHotelResponse();
        try {
            List<Hotel> hotels = hotelService.getHotelList(limit);
            List<HotelDTO> hotelDTOS = new LinkedList<>();
            hotels.forEach(hotel -> {
                hotelDTOS.add(new HotelDTO(hotel));
            });
            findHotelResponse.setHotels(hotelDTOS);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            findHotelResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            findHotelResponse.setResponseInfo(ex.getMessage());
        }
        return findHotelResponse;
    }
}
