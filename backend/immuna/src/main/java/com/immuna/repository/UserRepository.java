package com.immuna.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.immuna.entity.Role;
import com.immuna.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    
    List<User> findByRole(Role role);

}
