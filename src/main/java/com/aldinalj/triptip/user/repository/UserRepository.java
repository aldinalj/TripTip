package com.aldinalj.triptip.user.repository;

import com.aldinalj.triptip.user.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<CustomUser, Long> {

    Optional<CustomUser> findByUsername(String username);

    Optional<CustomUser> findByUsernameIgnoreCase(String username);
}
