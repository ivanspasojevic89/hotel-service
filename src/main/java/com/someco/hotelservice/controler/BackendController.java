package com.someco.hotelservice.controler;


import com.someco.hotelservice.api.request.CreateHotelRequest;
import com.someco.hotelservice.api.request.UpdateHotelRequest;
import com.someco.hotelservice.api.response.CreateHotelResponse;
import com.someco.hotelservice.api.response.HotelInfoResponse;
import com.someco.hotelservice.constant.CommonConstants;
import com.someco.hotelservice.dto.HotelDTO;
import com.someco.hotelservice.model.Hotel;
import com.someco.hotelservice.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/backend")
public class BackendController {

    @Autowired
    private HotelService hotelService;

    @RequestMapping(path = "/createHotel", method = RequestMethod.POST)
    public CreateHotelResponse createHotel(@RequestBody CreateHotelRequest createHotelRequest) {
        CreateHotelResponse createHotelResponse = new CreateHotelResponse();
        try {
            Hotel hotel = hotelService.createHotel(createHotelRequest);
            createHotelResponse.setHotelID(hotel.getHotelID());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            createHotelResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            createHotelResponse.setResponseInfo(ex.getMessage());

        }

        return createHotelResponse;
    }

    @RequestMapping(path = "/updateHotel", method = RequestMethod.POST)
    public HotelInfoResponse updateHotel(@RequestBody UpdateHotelRequest updateHotelRequest) {
        HotelInfoResponse hotelInfoResponse = new HotelInfoResponse();
        try {
            Hotel hotel = hotelService.updateHotel(updateHotelRequest);
            hotelInfoResponse.setHotel(new HotelDTO(hotel));
        } catch (Exception ex) {
            log.error(ex.getMessage());
            hotelInfoResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            hotelInfoResponse.setResponseInfo(ex.getMessage());

        }

        return hotelInfoResponse;
    }

}
