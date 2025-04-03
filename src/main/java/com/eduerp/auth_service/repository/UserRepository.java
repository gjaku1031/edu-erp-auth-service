package com.eduerp.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduerp.auth_service.entity.CustomUserDetails;

@Repository
public interface UserRepository extends JpaRepository<CustomUserDetails, Long>{

    Optional<CustomUserDetails> findByEmail(String email);

    boolean existsByEmail(String email);
    
}