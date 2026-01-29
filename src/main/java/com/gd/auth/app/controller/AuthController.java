package com.gd.auth.app.controller;

import com.gd.auth.app.dto.request.CreateAccountRequest;
import com.gd.auth.app.dto.request.LoginRequest;
import com.gd.auth.app.dto.response.AuthResponse;
import com.gd.auth.app.dto.response.UserResponse;
import com.gd.auth.app.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/create-account")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createAccount(@Valid @RequestBody CreateAccountRequest req) {
        return authService.createAccount(req);
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }
}
