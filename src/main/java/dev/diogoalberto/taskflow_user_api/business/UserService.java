package dev.diogoalberto.taskflow_user_api.business;

import dev.diogoalberto.taskflow_user_api.business.converter.UserConverter;
import dev.diogoalberto.taskflow_user_api.business.dto.AddressDTO;
import dev.diogoalberto.taskflow_user_api.business.dto.PhoneNumberDTO;
import dev.diogoalberto.taskflow_user_api.business.dto.UserDTO;
import dev.diogoalberto.taskflow_user_api.infrastructure.entity.Address;
import dev.diogoalberto.taskflow_user_api.infrastructure.entity.PhoneNumber;
import dev.diogoalberto.taskflow_user_api.infrastructure.entity.User;
import dev.diogoalberto.taskflow_user_api.infrastructure.exception.ConflictException;
import dev.diogoalberto.taskflow_user_api.infrastructure.exception.ResourceNotFoundException;
import dev.diogoalberto.taskflow_user_api.infrastructure.repository.AddressRepository;
import dev.diogoalberto.taskflow_user_api.infrastructure.repository.PhoneNumberRepository;
import dev.diogoalberto.taskflow_user_api.infrastructure.repository.UserRepository;
import dev.diogoalberto.taskflow_user_api.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserDTO saveUser(UserDTO userDTO){
        emailExists(userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userConverter.toUser(userDTO);
        user = userRepository.save(user);
        return userConverter.toUserDTO(user);
    }

    public void emailExists(String email){
        boolean exists = userRepository.existsUserByEmail(email);
        if(exists){
            throw new ConflictException("Email already registered: " + email);
        }
    }

    public UserDTO getUserByEmail(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found."));
        return userConverter.toUserDTO(user);
    }

    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public UserDTO updateUser(String token, UserDTO userDTO){
        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("User with email " + email + " not found."));

        if(userDTO.getPassword() != null){
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        User updatedUser = userConverter.updateUser(userDTO, user);

        return userConverter.toUserDTO(
                userRepository.save(updatedUser)
        );
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO){
        Address address = addressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Address with ID " + id + "not found"));

        Address updatedAddress = userConverter.updateAddress(addressDTO, address);

        return userConverter.toAddressDTO(addressRepository.save(updatedAddress));
    }

    public PhoneNumberDTO updatePhoneNumber(Long id, PhoneNumberDTO phoneNumberDTO){
        PhoneNumber phoneNumber = phoneNumberRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Phone number with ID " + id + "not found"));

        PhoneNumber updatedPhoneNumber = userConverter.updatePhoneNumber(phoneNumberDTO, phoneNumber);

        return userConverter.toPhoneNumberDTO(phoneNumberRepository.save(updatedPhoneNumber));
    }

    public AddressDTO createAddress(String token, AddressDTO addressDTO){
        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("User with email " + email + " not found."));

        Address address = userConverter.toAddress(addressDTO, user.getId());
        address = addressRepository.save(address);

        return userConverter.toAddressDTO(address);
    }
}
