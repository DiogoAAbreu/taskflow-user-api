package dev.diogoalberto.taskflow_user_api.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private List<AddressDTO> addressDTOList;
    private List<PhoneNumberDTO> phoneNumberDTOList;
}
