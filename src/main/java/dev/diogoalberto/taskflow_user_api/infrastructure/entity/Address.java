package dev.diogoalberto.taskflow_user_api.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private Long number;

    @Column(length = 30)
    private String complement;

    @Column(length = 100)
    private String city;

    @Column(length = 2)
    private String state;

    @Column(length = 8)
    private String cep;
}
