package dev.diogoalberto.taskflow_user_api.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private String street;
    private Long number;
    private String complement;
    private String city;
    private String state;
    private String cep;
}
