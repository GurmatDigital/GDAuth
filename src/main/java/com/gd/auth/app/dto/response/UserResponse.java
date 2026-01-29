package com.gd.auth.app.dto.response;
import lombok.*;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private Instant createdAt;
    private Set<String> roles;
}
