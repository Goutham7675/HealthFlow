package com.SpringDataJpa.Spring.JPA.repository;

import com.SpringDataJpa.Spring.JPA.entity.User;
import com.SpringDataJpa.Spring.JPA.entity.type.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;

//import java.lang.ScopedValue;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}