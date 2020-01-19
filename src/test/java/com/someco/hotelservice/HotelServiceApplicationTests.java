package com.someco.hotelservice;

import com.someco.hotelservice.dao.UserDAO;
import com.someco.hotelservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HotelServiceApplicationTests {

    @Autowired
    private UserDAO userDAO;

    @Test
    void createUsers() {
        User user = User.builder()
                .userDisplayName("Admin")
                .userEmail("test@someco.com")
                .userPassword("sadfsdeasdadf")
                .userRoleGroup("admin")
                .build();

        User user2 = User.builder()
                .userDisplayName("User")
                .userEmail("test2@someco.com")
                .userPassword("sadfsdsdafadfsaeasdadf")
                .userRoleGroup("user")
                .build();

        userDAO.save(user);
        userDAO.save(user2);
    }

}
