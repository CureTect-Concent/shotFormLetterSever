package com.shotFormLetter.sFL.domain.user.domain.repository;

import com.shotFormLetter.sFL.domain.user.domain.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SiteUser, Integer> {
    Optional<SiteUser> findByUsername(String username);
}
