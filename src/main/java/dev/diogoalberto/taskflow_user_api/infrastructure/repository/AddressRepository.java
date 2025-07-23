package dev.diogoalberto.taskflow_user_api.infrastructure.repository;

import dev.diogoalberto.taskflow_user_api.infrastructure.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
