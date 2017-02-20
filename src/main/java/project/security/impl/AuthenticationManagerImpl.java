package project.security.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.persistence.entities.User;
import project.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("authenticationManager")
public class AuthenticationManagerImpl implements AuthenticationManager {

    // Instance Variables
    UserService userService;

    // Dependency Injection
    @Autowired
    public AuthenticationManagerImpl(UserService userService) {
        this.userService = userService;
    }

    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Authentication authenticate(Authentication auth) throws AuthenticationException {


        User user =  userService.findByUsername(auth.getName());

        if(user != null) {
            if (auth.getCredentials().equals(user.getPass())) {
                return new UsernamePasswordAuthenticationToken(auth.getName(),
                        auth.getCredentials(), AUTHORITIES);
            }
        }

        throw new BadCredentialsException("Bad Credentials");
    }

}
