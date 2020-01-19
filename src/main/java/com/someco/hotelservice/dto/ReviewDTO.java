package com.someco.hotelservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.someco.hotelservice.model.Review;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewDTO {
    private Long reviewID;
    private String reviewDescription;
    private Integer reviewRate;
    private Date reviewCreated;
    private HotelDTO hotel;
    private UserSimpleDTO user;

    public ReviewDTO(Review review) {
        if (review != null) {
            reviewID = review.getReviewID();
            reviewDescription = review.getReviewDescription();
            reviewRate = review.getReviewRate();
            reviewCreated = review.getReviewCreated();
        }
    }
}
