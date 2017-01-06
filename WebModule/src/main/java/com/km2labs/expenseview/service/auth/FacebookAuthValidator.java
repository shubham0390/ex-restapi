package com.km2labs.expenseview.service.auth;

import com.km2labs.expenseview.common.authproviders.FacebookAuthProvider;

/**
 * Created by subhamtyagi on 06/01/17.
 */
public class FacebookAuthValidator implements AuthValidator<FacebookAuthProvider> {

    @Override
    public boolean validate(FacebookAuthProvider authProvider) {
        return true;
    }
}
