package com.someco.hotelservice.api.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.someco.hotelservice.dto.HotelDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FindHotelResponse extends StandardResponse {
    private List<HotelDTO> hotels;
    private Long totalCount;
}
