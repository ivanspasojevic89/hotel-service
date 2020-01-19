package com.someco.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.someco.hotelservice.model.Hotel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotelDTO {
    private Long hotelID;
    private String hotelName;
    private String hotelDescription;
    private String hotelAddress;
    private BigDecimal hotelLat;
    private BigDecimal hotelLon;
    private BigDecimal hotelOverallRating;
    private Serializable hotelImage;


    public HotelDTO(Hotel hotel) {
        if (hotel != null) {
            hotelID = hotel.getHotelID();
            hotelName = hotel.getHotelName();
            hotelAddress = hotel.getHotelAddress();
            hotelDescription = hotel.getHotelDescription();
            hotelLat = hotel.getHotelLat();
            hotelLon = hotel.getHotelLon();
            hotelOverallRating = hotel.getHotelOverallRating();
            hotelImage = hotel.getHotelImage();
        }
    }

}
