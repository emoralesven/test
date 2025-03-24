package com.api.rest.domain.ports.output;

import com.api.rest.domain.model.UserBank;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserBank, UUID> {
    Optional<UserBank> findByEmail(String email);
}
