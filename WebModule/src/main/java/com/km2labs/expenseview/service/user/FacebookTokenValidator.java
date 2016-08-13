package com.km2labs.expenseview.service.user;

import com.km2labs.expenseview.rest.model.User;

/**
 * Created by suze on 08/08/16.
 */
public class FacebookTokenValidator implements TokenValidator {
    @Override
    public boolean isValid(User user, String authenticationToken) {
        return false;
    }
}
