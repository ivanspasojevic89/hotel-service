package com.someco.hotelservice.util;

import com.someco.hotelservice.constant.CommonConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    public static boolean isValid(String email) {
        Pattern pattern = Pattern.compile(CommonConstants.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
