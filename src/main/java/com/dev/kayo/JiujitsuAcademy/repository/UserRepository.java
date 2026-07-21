package com.dev.kayo.JiujitsuAcademy.repository;

import com.dev.kayo.JiujitsuAcademy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
