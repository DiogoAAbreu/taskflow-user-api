package dev.diogoalberto.taskflow_user_api.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_phone_number")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Column(length = 3)
    private String ddd;

    @Column(name = "user_id")
    private Long userId;
}
