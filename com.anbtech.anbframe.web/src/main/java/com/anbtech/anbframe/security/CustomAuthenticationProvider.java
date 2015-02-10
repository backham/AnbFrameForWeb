package com.anbtech.anbframe.security;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;

import com.anbtech.anbframe.security.service.LoginService;
import com.anbtech.anbframe.security.service.domain.User;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    LoginService loginService;

    //@Autowired
    //private PasswordEncoder passwordEncoder;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //@Autowired
    private SaltSource saltSource;

  
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user;
        Collection<? extends GrantedAuthority> authorities;

        try {

            user = loginService.loadUserByUsername(username);

            String hashedPassword = passwordEncoder.encode(password);

            logger.info("username : " + username + " / password : " + password + " / hash password : " + hashedPassword);
            logger.info("username : " + user.getUsername() + " / password : " + user.getPassword());

           // if (!hashedPassword.equals(user.getPassword())) throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
            if (!passwordEncoder.matches(password, user.getPassword())) throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");

            authorities = user.getAuthorities();
        } catch(UsernameNotFoundException e) {
            logger.info(e.toString());
            throw new UsernameNotFoundException(e.getMessage());
        } catch(BadCredentialsException e) {
            logger.info(e.toString());
            throw new BadCredentialsException(e.getMessage());
        } catch(Exception e) {
            logger.info(e.toString());
            throw new RuntimeException(e.getMessage());
        }

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
    }

 
    public boolean supports(Class<?> arg0) {
        return true;
    }
}