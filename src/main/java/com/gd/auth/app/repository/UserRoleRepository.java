package com.gd.auth.app.repository;

import com.gd.auth.app.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("""
      select ur.role.name
      from UserRole ur
      where ur.user.id = :userId
      """)
    Set<String> findRoleNamesByUserId(Long userId);
}

