package dev.diogoalberto.taskflow_user_api.business;

import dev.diogoalberto.taskflow_user_api.business.converter.UserConverter;
import dev.diogoalberto.taskflow_user_api.business.dto.UserDTO;
import dev.diogoalberto.taskflow_user_api.infrastructure.entity.User;
import dev.diogoalberto.taskflow_user_api.infrastructure.exception.ConflictException;
import dev.diogoalberto.taskflow_user_api.infrastructure.exception.ResourceNotFound;
import dev.diogoalberto.taskflow_user_api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

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
                .orElseThrow(() -> new ResourceNotFound("User with email " + email + " not found."));
        return userConverter.toUserDTO(user);
    }
}
