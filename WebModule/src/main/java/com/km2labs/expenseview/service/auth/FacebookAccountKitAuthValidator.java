package com.km2labs.expenseview.service.auth;

import com.km2labs.expenseview.common.authproviders.FacebookAccountKitAuthProvider;

/**
 * Created by subhamtyagi on 06/01/17.
 */
public class FacebookAccountKitAuthValidator implements AuthValidator<FacebookAccountKitAuthProvider> {

    @Override
    public boolean validate(FacebookAccountKitAuthProvider authProvider) {
        return false;
    }
}
