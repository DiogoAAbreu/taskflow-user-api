package dev.diogoalberto.taskflow_user_api.controller;

import dev.diogoalberto.taskflow_user_api.business.UserService;
import dev.diogoalberto.taskflow_user_api.business.dto.AddressDTO;
import dev.diogoalberto.taskflow_user_api.business.dto.PhoneNumberDTO;
import dev.diogoalberto.taskflow_user_api.business.dto.UserDTO;
import dev.diogoalberto.taskflow_user_api.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    final private UserService userService;
    final private AuthenticationManager authenticationManager;
    final private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @GetMapping
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );

        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,
                                              @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.updateUser(token, userDTO));
    }

    @PutMapping("/address/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO){
        return ResponseEntity.ok(userService.updateAddress(id, addressDTO));
    }

    @PutMapping("/phone-number/{id}")
    public ResponseEntity<PhoneNumberDTO> updatePhoneNumber(@PathVariable Long id, @RequestBody PhoneNumberDTO phoneNumberDTO){
        return ResponseEntity.ok(userService.updatePhoneNumber(id, phoneNumberDTO));
    }
}
