package com.km2labs.expenseview.service.auth;

import com.km2labs.expenseview.common.authproviders.TwitterDigitAuthProvider;

/**
 * Created by subhamtyagi on 06/01/17.
 */
public class TwitterDigitAuthValidator implements AuthValidator<TwitterDigitAuthProvider> {

    @Override
    public boolean validate(TwitterDigitAuthProvider authProvider) {
        return false;
    }
}
