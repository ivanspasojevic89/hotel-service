package com.someco.hotelservice.dto;

import com.someco.hotelservice.model.User;
import lombok.Data;

@Data
public class UserSimpleDTO {

    private Long userID;
    private String userDisplayName;
    private String userEmail;

    public UserSimpleDTO(User user) {
        if (user != null) {
            userID = user.getUserID();
            userDisplayName = user.getUserDisplayName();
            userEmail = user.getUserEmail();
        }
    }
}
