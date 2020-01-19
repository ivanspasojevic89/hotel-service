package com.someco.hotelservice.service.impl;

import com.someco.hotelservice.api.request.CreateReviewRequest;
import com.someco.hotelservice.api.request.ReviewConfirmationRequest;
import com.someco.hotelservice.dao.HotelDAO;
import com.someco.hotelservice.dao.ReviewConfirmationDAO;
import com.someco.hotelservice.dao.ReviewDAO;
import com.someco.hotelservice.dao.UserDAO;
import com.someco.hotelservice.model.Hotel;
import com.someco.hotelservice.model.Review;
import com.someco.hotelservice.model.ReviewConfirmation;
import com.someco.hotelservice.model.User;
import com.someco.hotelservice.service.HotelService;
import com.someco.hotelservice.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private HotelDAO hotelDAO;

    @Autowired
    private ReviewDAO reviewDAO;

    @Autowired
    private ReviewConfirmationDAO reviewConfirmationDAO;

    @Autowired
    private HotelService hotelService;

    @Override
    public Review createReview(CreateReviewRequest createReviewRequest) throws Exception {

        Optional<User> user = userDAO.findById(createReviewRequest.getUserID());
        if (!user.isPresent()) {
            throw new Exception("Invalid userID");
        }

        Optional<Hotel> hotel = hotelDAO.findById(createReviewRequest.getHotelID());
        if (!hotel.isPresent()) {
            throw new Exception("Invalid hotelID");
        }

        Review review = Review.builder()
                .reviewDescription(createReviewRequest.getDescription())
                .reviewRate(createReviewRequest.getRate())
                .reviewCalculatedRate(new BigDecimal(createReviewRequest.getRate()))
                .user(user.get())
                .hotel(hotel.get())
                .build();

        reviewDAO.save(review);
        hotelService.calculateOverallRate(hotel.get().getHotelID());
        return review;
    }

    @Override
    public List<Review> getReviewList(Long hotelID) {
        return reviewDAO.findAllByHotelID(hotelID);
    }

    @Override
    public ReviewConfirmation makeConfirmation(ReviewConfirmationRequest reviewConfirmationRequest, String reviewType) throws Exception {
        Optional<User> user = userDAO.findById(reviewConfirmationRequest.getUserID());
        if (!user.isPresent()) {
            throw new Exception("Invalid userID");
        }

        Optional<Review> review = reviewDAO.findById(reviewConfirmationRequest.getReviewID());
        if (!review.isPresent()) {
            throw new Exception("Invalid reviewID");
        }

        ReviewConfirmation reviewConfirmation = ReviewConfirmation.builder()
                .reviewConfirmationType(reviewType)
                .review(review.get())
                .user(user.get())
                .build();

        reviewConfirmationDAO.save(reviewConfirmation);

        return reviewConfirmation;
    }

    @Override
    public ReviewConfirmation deleteConfirmation(ReviewConfirmationRequest reviewConfirmationRequest, String confirmationType) throws Exception {
        Optional<ReviewConfirmation> reviewConfirmation = reviewConfirmationDAO.findByUserAndReview(reviewConfirmationRequest.getReviewID(), reviewConfirmationRequest.getUserID(), confirmationType);
        if (!reviewConfirmation.isPresent()) {
            throw new Exception("Like or dislike not found for specified parameters");
        }
        reviewConfirmationDAO.delete(reviewConfirmation.get());
        return reviewConfirmation.get();
    }

    @Override
    public List<User> getReviewers(Long reviewID, String reviewType) {
        return userDAO.findUsersInReviewConfirmation(reviewID, reviewType);
    }
}
