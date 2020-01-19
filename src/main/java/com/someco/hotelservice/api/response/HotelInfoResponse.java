package com.someco.hotelservice.api.response;


import com.someco.hotelservice.dto.HotelDTO;
import lombok.Data;

@Data
public class HotelInfoResponse extends StandardResponse {
    private HotelDTO hotel;
}
