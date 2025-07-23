package dev.diogoalberto.taskflow_user_api.infrastructure.repository;

import dev.diogoalberto.taskflow_user_api.infrastructure.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
}
