package com.xinyue.blog.security;

import com.xinyue.blog.service.security.CustomUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserServiceImpl customUserServiceImpl;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public CustomAuthenticationProvider(CustomUserServiceImpl customUserServiceImpl, PasswordEncoder passwordEncoder) {
        this.customUserServiceImpl = customUserServiceImpl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(name);
        if (userDetails != null) {
            String password = authentication.getCredentials().toString();
            if (passwordEncoder.matches(password, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(name, password, userDetails.getAuthorities());
            }
        }
        throw new BadCredentialsException("密码错误~");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
