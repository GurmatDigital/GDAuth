package com.gd.auth.app.service;

import com.gd.auth.app.dto.request.CreateAccountRequest;
import com.gd.auth.app.dto.request.LoginRequest;
import com.gd.auth.app.dto.response.AuthResponse;
import com.gd.auth.app.dto.response.UserResponse;
import com.gd.auth.app.entity.Role;
import com.gd.auth.app.entity.User;
import com.gd.auth.app.entity.UserRole;
import com.gd.auth.app.repository.RoleRepository;
import com.gd.auth.app.repository.UserRepository;
import com.gd.auth.app.repository.UserRoleRepository;
import com.gd.auth.app.security.JwtService;
import com.gd.auth.app.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public UserResponse createAccount(CreateAccountRequest req) {
        String email = req.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = User.builder()
                .email(email)
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .firstName(req.getFirstName().trim())
                .lastName(req.getLastName().trim())
                .enabled(true)
                .build();

        user = userRepository.save(user);

        Role userRole = roleRepository.findByName(Constants.ROLE_USER)
                .orElseThrow(() -> new IllegalStateException("Default role USER not seeded"));

        userRoleRepository.save(UserRole.builder().user(user).role(userRole).build());

        Set<String> roles = userRoleRepository.findRoleNamesByUserId(user.getId());

        return toUserResponse(user, roles);
    }

    public AuthResponse login(LoginRequest req) {
        String email = req.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!user.isEnabled()) {
            throw new IllegalArgumentException("Account disabled");
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        Set<String> roles = userRoleRepository.findRoleNamesByUserId(user.getId());
        String token = jwtService.generateAccessToken(user.getId(), user.getEmail(), roles);

        return AuthResponse.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .user(toUserResponse(user, roles))
                .build();
    }

    private UserResponse toUserResponse(User user, Set<String> roles) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedAt())
                .roles(roles)
                .build();
    }
}
