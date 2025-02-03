package com.bankingsystem.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String principal;

    public JwtAuthenticationToken(String principal, Object credentials, Object details) {
        super(Collections.singletonList(new SimpleGrantedAuthority("USER")));
        this.principal = principal;
        this.setDetails(details);
    }

    @Override
    public Object getCredentials() {
        return null; // No credentials, as we are using JWT
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
