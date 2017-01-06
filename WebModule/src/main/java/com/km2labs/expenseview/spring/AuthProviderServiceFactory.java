package com.km2labs.expenseview.spring;

import com.km2labs.expenseview.common.authproviders.AuthProvider;
import com.km2labs.expenseview.common.authproviders.FacebookAccountKitAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Created by subhamtyagi on 06/01/17.
 */
@Configuration
public class AuthProviderServiceFactory {

    @Bean
    @Conditional(AuthProviderCondition.class)
    public AuthProvider facebook() {
        return new FacebookAccountKitAuthProvider();
    }

    @Bean
    @Conditional(AuthProviderCondition.class)
    public AuthProvider google() {
        return new FacebookAccountKitAuthProvider();
    }

    @Bean
    @Conditional(AuthProviderCondition.class)
    public AuthProvider firbase() {
        return new FacebookAccountKitAuthProvider();
    }

    @Bean
    @Conditional(AuthProviderCondition.class)
    public AuthProvider twitterDigit() {
        return new FacebookAccountKitAuthProvider();
    }

    @Bean
    @Conditional(AuthProviderCondition.class)
    public AuthProvider facebookAccountKit() {
        return new FacebookAccountKitAuthProvider();
    }
}
