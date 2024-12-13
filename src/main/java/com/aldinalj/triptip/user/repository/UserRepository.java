package com.aldinalj.triptip.user.repository;

import com.aldinalj.triptip.user.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CustomUser, Long> {
}
