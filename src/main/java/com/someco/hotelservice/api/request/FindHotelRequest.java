package com.someco.hotelservice.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindHotelRequest {
    private String name;
    private String address;

    private int limit;
    private int offset;

}
