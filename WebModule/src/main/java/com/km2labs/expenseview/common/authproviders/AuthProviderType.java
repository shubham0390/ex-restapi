package com.km2labs.expenseview.common.authproviders;

import lombok.Getter;

/**
 * Created by subhamtyagi on 06/01/17.
 */
@Getter
public enum AuthProviderType {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    FIREBASE("firbase"),
    TWITTER_DIGITS("twitterDigit"),
    FACEBOOK_ACCOUNT_KIT("facebookAccountKit");

    private String name;

    AuthProviderType(String name) {
        this.name = name;
    }

}
