package com.someco.hotelservice.api.response;

import lombok.Data;

@Data
public class CreateHotelResponse extends StandardResponse {
    private Long hotelID;
}
