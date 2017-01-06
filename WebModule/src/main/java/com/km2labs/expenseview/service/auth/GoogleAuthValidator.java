package com.km2labs.expenseview.service.auth;

import com.km2labs.expenseview.common.authproviders.GoogleAuthProvider;

/**
 * Created by subhamtyagi on 06/01/17.
 */
public class GoogleAuthValidator implements AuthValidator<GoogleAuthProvider> {

    @Override
    public boolean validate(GoogleAuthProvider authProvider) {
        return false;
    }
}
