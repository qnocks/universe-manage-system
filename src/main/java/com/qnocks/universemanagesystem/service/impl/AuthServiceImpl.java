package com.qnocks.universemanagesystem.service.impl;

import com.qnocks.universemanagesystem.domain.Status;
import com.qnocks.universemanagesystem.domain.User;
import com.qnocks.universemanagesystem.dto.LoginRequest;
import com.qnocks.universemanagesystem.dto.LoginResponse;
import com.qnocks.universemanagesystem.dto.SignupRequest;
import com.qnocks.universemanagesystem.security.jwt.JwtTokenProvider;
import com.qnocks.universemanagesystem.service.AuthService;
import com.qnocks.universemanagesystem.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder encoder;

    private final UserService userService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();

        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));

        User user = userService.getByUsername(username);
        String token = jwtTokenProvider.createToken(username, user.getRoles());

        return new LoginResponse(username, token);
    }

    @Override
    public void register(SignupRequest signupRequest) {
        User user = User.builder()
                .username(signupRequest.getUsername())
                .password(encoder.encode(signupRequest.getPassword()))
                .status(Status.ACTIVE)
                .build();

        userService.save(user);
    }
}
