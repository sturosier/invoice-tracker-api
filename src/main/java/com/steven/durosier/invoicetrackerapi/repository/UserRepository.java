package com.steven.durosier.invoicetrackerapi.repository;

import com.steven.durosier.invoicetrackerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(final String email);

    Optional<User> findUserByEmail(final String email);
}
