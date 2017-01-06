package com.km2labs.expenseview.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by subhamtyagi on 06/01/17.
 */
@Slf4j
public class AuthProviderCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        AuthProviderServiceFactory authProviderServiceFactory = null;
        try {
            authProviderServiceFactory = (AuthProviderServiceFactory) conditionContext.getBeanFactory().getBean("authProviderFactory");
        } catch (NoSuchBeanDefinitionException ex) {
            log.debug("unable to find factory", ex);
        }
        return authProviderServiceFactory != null;
    }
}
