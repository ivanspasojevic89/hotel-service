package com.someco.hotelservice.service;

import com.someco.hotelservice.api.request.CreateReviewRequest;
import com.someco.hotelservice.api.request.ReviewConfirmationRequest;
import com.someco.hotelservice.model.Review;
import com.someco.hotelservice.model.ReviewConfirmation;
import com.someco.hotelservice.model.User;

import java.util.List;

public interface ReviewService {
    Review createReview(CreateReviewRequest createReviewRequest) throws Exception;

    List<Review> getReviewList(Long hotelID);

    ReviewConfirmation makeConfirmation(ReviewConfirmationRequest reviewConfirmationRequest, String dislike) throws Exception;

    ReviewConfirmation deleteConfirmation(ReviewConfirmationRequest reviewConfirmationRequest, String confirmationType) throws Exception;

    List<User> getReviewers(Long reviewID, String reviewType);
}
