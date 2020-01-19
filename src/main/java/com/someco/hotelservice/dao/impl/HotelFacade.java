package com.someco.hotelservice.dao.impl;


import com.someco.hotelservice.api.request.FindHotelRequest;
import com.someco.hotelservice.model.Hotel;

import java.util.List;

public interface HotelFacade {

    List<Hotel> findHotelsByCriteria(FindHotelRequest findHotelRequest, String orderProperty, String orderType);

    Long getTotalSize(FindHotelRequest findHotelRequest);

}
