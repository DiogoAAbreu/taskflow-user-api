package dev.diogoalberto.taskflow_user_api.infrastructure.repository;

import dev.diogoalberto.taskflow_user_api.infrastructure.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
