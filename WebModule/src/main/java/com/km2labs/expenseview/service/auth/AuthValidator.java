package com.km2labs.expenseview.service.auth;

import com.km2labs.expenseview.common.authproviders.AuthProvider;
import org.springframework.stereotype.Service;

/**
 * Created by subhamtyagi on 06/01/17.
 */
@Service
public interface AuthValidator<T extends AuthProvider> {

    boolean validate(T authProvider);
}
