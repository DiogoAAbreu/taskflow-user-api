package dev.diogoalberto.taskflow_user_api.business.converter;

import dev.diogoalberto.taskflow_user_api.business.dto.AddressDTO;
import dev.diogoalberto.taskflow_user_api.business.dto.PhoneNumberDTO;
import dev.diogoalberto.taskflow_user_api.business.dto.UserDTO;
import dev.diogoalberto.taskflow_user_api.infrastructure.entity.Address;
import dev.diogoalberto.taskflow_user_api.infrastructure.entity.PhoneNumber;
import dev.diogoalberto.taskflow_user_api.infrastructure.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {
    public User toUser(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .addressList(toAddressList(userDTO.getAddressList()))
                .phoneNumberList(toPhoneNumberList(userDTO.getPhoneNumberList()))
                .build();
    }

    public List<Address> toAddressList(List<AddressDTO> addressList){
        return addressList.stream().map(this::toAddress).toList();
    }

    public Address toAddress(AddressDTO addressDTO){
        return Address.builder()
                .street(addressDTO.getStreet())
                .number(addressDTO.getNumber())
                .complement(addressDTO.getComplement())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .cep(addressDTO.getCep())
                .build();
    }

    public Address toAddress(AddressDTO addressDTO, Long userId){
        return Address.builder()
                .street(addressDTO.getStreet())
                .number(addressDTO.getNumber())
                .complement(addressDTO.getComplement())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .cep(addressDTO.getCep())
                .userId(userId)
                .build();
    }

    public List<PhoneNumber> toPhoneNumberList(List<PhoneNumberDTO> phoneNumberList){
        return phoneNumberList.stream().map(this::toPhoneNumber).toList();
    }

    public PhoneNumber toPhoneNumber(PhoneNumberDTO phoneNumberDTO){
        return PhoneNumber.builder()
                .phoneNumber(phoneNumberDTO.getPhoneNumber())
                .ddd(phoneNumberDTO.getDdd())
                .build();
    }

    public UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addressList(toAddressDTOList(user.getAddressList()))
                .phoneNumberList(toPhoneNumberDTOList(user.getPhoneNumberList()))
                .build();
    }

    public List<AddressDTO> toAddressDTOList(List<Address> addressList){
        return addressList.stream().map(this::toAddressDTO).toList();
    }

    public AddressDTO toAddressDTO(Address address){
        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .city(address.getCity())
                .state(address.getState())
                .cep(address.getCep())
                .build();
    }

    public List<PhoneNumberDTO> toPhoneNumberDTOList(List<PhoneNumber> phoneNumberList){
        return phoneNumberList.stream().map(this::toPhoneNumberDTO).toList();
    }

    public PhoneNumberDTO toPhoneNumberDTO(PhoneNumber phoneNumber){
        return PhoneNumberDTO.builder()
                .id(phoneNumber.getId())
                .phoneNumber(phoneNumber.getPhoneNumber())
                .ddd(phoneNumber.getDdd())
                .build();
    }

    public User updateUser(UserDTO userDTO, User user){
        return User.builder()
                .id(user.getId())
                .name(userDTO.getName() != null ? userDTO.getName() : user.getName())
                .password(userDTO.getPassword() != null ? userDTO.getPassword() : user.getPassword())
                .email(userDTO.getEmail() != null ? userDTO.getEmail() : user.getEmail())
                .addressList(user.getAddressList())
                .phoneNumberList(user.getPhoneNumberList())
                .build();
    }

    public Address updateAddress(AddressDTO addressDTO, Address address){
        return Address.builder()
                .id(address.getId())
                .street(addressDTO.getStreet() != null ? addressDTO.getStreet() : address.getStreet())
                .number(addressDTO.getNumber() != null ? addressDTO.getNumber() : address.getNumber())
                .complement(addressDTO.getComplement() != null ? addressDTO.getComplement() : address.getComplement())
                .city(addressDTO.getCity() != null ? addressDTO.getCity() : address.getCity())
                .state(addressDTO.getState() != null ? addressDTO.getState() : address.getState())
                .cep(addressDTO.getCep() != null ? addressDTO.getCep() : address.getCep())
                .build();
    }

    public PhoneNumber updatePhoneNumber(PhoneNumberDTO phoneNumberDTO, PhoneNumber phoneNumber){
        return PhoneNumber.builder()
                .id(phoneNumber.getId())
                .ddd(phoneNumberDTO.getDdd() != null ? phoneNumberDTO.getDdd() : phoneNumber.getDdd())
                .phoneNumber(phoneNumberDTO.getPhoneNumber() != null ? phoneNumberDTO.getPhoneNumber() : phoneNumber.getPhoneNumber())
                .build();
    }
}
