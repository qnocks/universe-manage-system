package com.qnocks.universemanagesystem.service;

import com.qnocks.universemanagesystem.dto.LoginRequest;
import com.qnocks.universemanagesystem.dto.LoginResponse;
import com.qnocks.universemanagesystem.dto.SignupRequest;

public interface AuthService {

    LoginResponse login(LoginRequest loginRequest);

    void register(SignupRequest signupRequest);
}
