package dev.diogoalberto.taskflow_user_api.business;

import dev.diogoalberto.taskflow_user_api.business.converter.UserConverter;
import dev.diogoalberto.taskflow_user_api.business.dto.UserDTO;
import dev.diogoalberto.taskflow_user_api.infrastructure.entity.User;
import dev.diogoalberto.taskflow_user_api.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO saveUser(UserDTO userDTO){
        User user = userConverter.toUser(userDTO);
        user = userRepository.save(user);
        return userConverter.toUserDTO(user);
    }
}
