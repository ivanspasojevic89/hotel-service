package com.someco.hotelservice.api.response;

import lombok.Data;

@Data
public class CreateReviewResponse extends StandardResponse {
    private Long reviewID;
}
