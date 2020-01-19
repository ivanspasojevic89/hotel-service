package com.someco.hotelservice.controler;


import com.someco.hotelservice.api.request.CreateUserRequest;
import com.someco.hotelservice.api.response.CreateUserResponse;
import com.someco.hotelservice.constant.CommonConstants;
import com.someco.hotelservice.util.EmailValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(path = "/user")
public class UserController {

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public CreateUserResponse registerUserAccount(@RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse response = new CreateUserResponse();
        try {
            if (!EmailValidator.isValid(createUserRequest.getEmail())) {
                response.setResultCode(CommonConstants.INVALID_BODY);
                response.setResponseInfo("Invalid email");
                return response;
            }

            if (createUserRequest.getPassword() == null || !createUserRequest.getPassword().equals(createUserRequest.getMatchingPassword())) {
                response.setResultCode(CommonConstants.INVALID_BODY);
                response.setResponseInfo("Invalid email");
                return response;
            }
            //TODO: finish registration process

        } catch (Exception ex) {
            log.error(ex.getMessage());
            response.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            response.setResponseInfo(ex.getMessage());

        }

        return response;
    }
}
