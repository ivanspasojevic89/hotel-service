package com.someco.hotelservice.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewConfirmationRequest {
    private Long userID;
    private Long reviewID;
}
