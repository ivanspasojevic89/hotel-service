package com.someco.hotelservice.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewRequest {
    private String description;
    private Integer rate;
    private Long userID;
    private Long hotelID;
}
