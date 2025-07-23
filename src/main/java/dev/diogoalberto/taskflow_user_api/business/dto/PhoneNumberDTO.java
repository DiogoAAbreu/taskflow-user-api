package dev.diogoalberto.taskflow_user_api.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneNumberDTO {
    private String phoneNumber;
    private String ddd;
}
