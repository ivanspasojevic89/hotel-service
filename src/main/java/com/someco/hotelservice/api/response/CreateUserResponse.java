package com.someco.hotelservice.api.response;

import lombok.Data;

@Data
public class CreateUserResponse extends StandardResponse {
    private Long userID;
}
