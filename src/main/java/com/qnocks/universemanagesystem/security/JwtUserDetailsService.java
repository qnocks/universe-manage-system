package com.qnocks.universemanagesystem.security;

import com.qnocks.universemanagesystem.domain.User;
import com.qnocks.universemanagesystem.exception.ResourceNotFoundException;
import com.qnocks.universemanagesystem.service.UserService;
import com.qnocks.universemanagesystem.util.JwtUserDetailsFactory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return retrieveUserDetails(username);
        } catch (ResourceNotFoundException e) {
            log.warn("IN loadUserByUsername - " + e.getMessage());
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

    private UserDetails retrieveUserDetails(String username) {
        User user = userService.getByUsername(username);
        return JwtUserDetailsFactory.create(user);
    }
}
