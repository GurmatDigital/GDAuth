package com.gd.auth.app.config;


import com.gd.auth.app.entity.Role;
import com.gd.auth.app.repository.RoleRepository;
import com.gd.auth.app.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        roleRepository.findByName(Constants.ROLE_USER)
                .orElseGet(() -> roleRepository.save(Role.builder().name(Constants.ROLE_USER).build()));
    }
}

