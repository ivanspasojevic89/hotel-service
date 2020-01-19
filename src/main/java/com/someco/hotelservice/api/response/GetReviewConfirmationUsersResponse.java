package com.someco.hotelservice.api.response;

import com.someco.hotelservice.dto.UserSimpleDTO;
import lombok.Data;

import java.util.List;

@Data
public class GetReviewConfirmationUsersResponse extends StandardResponse {
    private List<UserSimpleDTO> users;
}
