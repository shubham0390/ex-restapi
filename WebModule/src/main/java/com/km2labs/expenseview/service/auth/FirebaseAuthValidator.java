package com.km2labs.expenseview.service.auth;

import com.km2labs.expenseview.common.authproviders.FirbaseAuthProvider;

/**
 * Created by subhamtyagi on 06/01/17.
 */
public class FirebaseAuthValidator implements AuthValidator<FirbaseAuthProvider> {

    @Override
    public boolean validate(FirbaseAuthProvider authProvider) {
        return false;
    }
}
