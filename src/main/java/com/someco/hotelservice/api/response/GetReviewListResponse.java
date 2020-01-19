package com.someco.hotelservice.api.response;


import com.someco.hotelservice.dto.ReviewDTO;
import lombok.Data;

import java.util.List;

@Data
public class GetReviewListResponse extends StandardResponse {
    private List<ReviewDTO> reviews;
}
