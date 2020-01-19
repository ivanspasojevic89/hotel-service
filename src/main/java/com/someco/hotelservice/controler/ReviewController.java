package com.someco.hotelservice.controler;

import com.someco.hotelservice.api.request.CreateReviewRequest;
import com.someco.hotelservice.api.request.ReviewConfirmationRequest;
import com.someco.hotelservice.api.response.CreateReviewResponse;
import com.someco.hotelservice.api.response.GetReviewConfirmationUsersResponse;
import com.someco.hotelservice.api.response.GetReviewListResponse;
import com.someco.hotelservice.api.response.ReviewConfirmationResponse;
import com.someco.hotelservice.constant.CommonConstants;
import com.someco.hotelservice.dto.ReviewDTO;
import com.someco.hotelservice.dto.UserSimpleDTO;
import com.someco.hotelservice.model.Review;
import com.someco.hotelservice.model.ReviewConfirmation;
import com.someco.hotelservice.model.User;
import com.someco.hotelservice.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/api/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(path = "/createReview", method = RequestMethod.POST)
    public CreateReviewResponse createReview(@RequestBody CreateReviewRequest createReviewRequest) {
        CreateReviewResponse createReviewResponse = new CreateReviewResponse();
        try {
            Review review = reviewService.createReview(createReviewRequest);
            createReviewResponse.setReviewID(review.getReviewID());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            createReviewResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            createReviewResponse.setResponseInfo(ex.getMessage());

        }
        return createReviewResponse;
    }

    @RequestMapping(path = "/getReviewList", method = RequestMethod.GET)
    public GetReviewListResponse getReviewList(@RequestParam Long hotelID) {
        GetReviewListResponse getReviewListResponse = new GetReviewListResponse();
        try {
            List<Review> reviewList = reviewService.getReviewList(hotelID);
            List<ReviewDTO> reviewDTOS = new LinkedList<>();
            reviewList.forEach(review -> {
                ReviewDTO reviewDTO = new ReviewDTO(review);
                reviewDTO.setUser(new UserSimpleDTO(review.getUser()));
                reviewDTOS.add(reviewDTO);
            });
            getReviewListResponse.setReviews(reviewDTOS);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            getReviewListResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            getReviewListResponse.setResponseInfo(ex.getMessage());
        }
        return getReviewListResponse;
    }

    @RequestMapping(path = "/likeReview", method = RequestMethod.POST)
    public ReviewConfirmationResponse likeReview(@RequestBody ReviewConfirmationRequest reviewConfirmationRequest) {
        ReviewConfirmationResponse reviewConfirmationResponse = new ReviewConfirmationResponse();
        try {
            ReviewConfirmation reviewConfirmation = reviewService.makeConfirmation(reviewConfirmationRequest, CommonConstants.LIKE);
            reviewConfirmationResponse.setReviewConfirmationID(reviewConfirmation.getReviewConfirmationID());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            reviewConfirmationResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            reviewConfirmationResponse.setResponseInfo(ex.getMessage());
        }
        return reviewConfirmationResponse;
    }

    @RequestMapping(path = "/dislikeReview", method = RequestMethod.POST)
    public ReviewConfirmationResponse dislikeReview(@RequestBody ReviewConfirmationRequest reviewConfirmationRequest) {
        ReviewConfirmationResponse reviewConfirmationResponse = new ReviewConfirmationResponse();
        try {
            ReviewConfirmation reviewConfirmation = reviewService.makeConfirmation(reviewConfirmationRequest, CommonConstants.DISLIKE);
            reviewConfirmationResponse.setReviewConfirmationID(reviewConfirmation.getReviewConfirmationID());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            reviewConfirmationResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            reviewConfirmationResponse.setResponseInfo(ex.getMessage());
        }
        return reviewConfirmationResponse;
    }

    @RequestMapping(path = "/undoDislikeReview", method = RequestMethod.POST)
    public ReviewConfirmationResponse undoDislikeReview(@RequestBody ReviewConfirmationRequest reviewConfirmationRequest) {
        return removePreviousConfiguration(reviewConfirmationRequest, CommonConstants.DISLIKE);
    }

    @RequestMapping(path = "/undoLikeReview", method = RequestMethod.POST)
    public ReviewConfirmationResponse undoLikeReview(@RequestBody ReviewConfirmationRequest reviewConfirmationRequest) {
        return removePreviousConfiguration(reviewConfirmationRequest, CommonConstants.LIKE);
    }

    @RequestMapping(path = "/getPositiveReviewers", method = RequestMethod.GET)
    public GetReviewConfirmationUsersResponse getPositiveReviewers(@RequestParam Long reviewID) {
        return getReviewers(reviewID, CommonConstants.LIKE);
    }

    @RequestMapping(path = "/getNegativeReviewers", method = RequestMethod.GET)
    public GetReviewConfirmationUsersResponse getNegativeReviewers(@RequestParam Long reviewID) {
        return getReviewers(reviewID, CommonConstants.DISLIKE);
    }

    private GetReviewConfirmationUsersResponse getReviewers(Long reviewID, String reviewType) {
        GetReviewConfirmationUsersResponse getReviewConfirmationUsersResponse = new GetReviewConfirmationUsersResponse();
        try {
            List<User> userList = reviewService.getReviewers(reviewID, reviewType);
            List<UserSimpleDTO> userSimpleDTOS = new LinkedList<>();
            userList.forEach(user -> {
                userSimpleDTOS.add(new UserSimpleDTO(user));
            });
            getReviewConfirmationUsersResponse.setUsers(userSimpleDTOS);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            getReviewConfirmationUsersResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            getReviewConfirmationUsersResponse.setResponseInfo(ex.getMessage());
        }
        return getReviewConfirmationUsersResponse;
    }


    private ReviewConfirmationResponse removePreviousConfiguration(ReviewConfirmationRequest reviewConfirmationRequest, String confirmationType) {
        ReviewConfirmationResponse reviewConfirmationResponse = new ReviewConfirmationResponse();
        try {
            ReviewConfirmation reviewConfirmation = reviewService.deleteConfirmation(reviewConfirmationRequest, confirmationType);
            reviewConfirmationResponse.setReviewConfirmationID(reviewConfirmation.getReviewConfirmationID());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            reviewConfirmationResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            reviewConfirmationResponse.setResponseInfo(ex.getMessage());
        }
        return reviewConfirmationResponse;
    }
}
