package com.qnocks.universemanagesystem.util;

import com.qnocks.universemanagesystem.domain.Role;
import com.qnocks.universemanagesystem.domain.Status;
import com.qnocks.universemanagesystem.domain.User;
import com.qnocks.universemanagesystem.security.JwtUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserDetailsFactory {

    public static JwtUserDetails create(User user) {
        return new JwtUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getStatus().equals(Status.ACTIVE),
                toGrantedAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> toGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
