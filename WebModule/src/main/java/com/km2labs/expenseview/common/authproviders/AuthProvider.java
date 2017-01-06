package com.km2labs.expenseview.common.authproviders;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by subhamtyagi on 06/01/17.
 */
@Getter
@Setter
public abstract class AuthProvider {
    AuthProviderType authProviderType;

}
