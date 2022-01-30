package com.qnocks.universemanagesystem.rest;

import com.qnocks.universemanagesystem.dto.LoginRequest;
import com.qnocks.universemanagesystem.dto.LoginResponse;
import com.qnocks.universemanagesystem.dto.SignupRequest;
import com.qnocks.universemanagesystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthRestControllerV1 {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {
        authService.register(signupRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }
}
