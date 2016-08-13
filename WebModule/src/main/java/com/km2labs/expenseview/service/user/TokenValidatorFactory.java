package com.km2labs.expenseview.service.user;

import com.km2labs.expenseview.service.LoginType;

/**
 * Created by suze on 08/08/16.
 */
public class TokenValidatorFactory {

    public static TokenValidator getTokenValidaor(String loginType) {
        LoginType type = LoginType.valueOf(loginType);
        switch (type) {
            case FACEBOOK:
                return new FacebookTokenValidator();
            case GOOGLE:
                return new GoogleTokenValidator();
            default:
                throw new IllegalStateException("Invalid login type");
        }
    }
}
