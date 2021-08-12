package com.jessy.user.aop;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null) {
            if (authentication instanceof AnonymousAuthenticationToken) {
                return Optional.ofNullable("Anonymous");
            }
        } else {
            return Optional.ofNullable("Anonymous");
        }
        return Optional.ofNullable(authentication.getName());
    }
}