package com.api.rest.api.controller;


import com.api.rest.application.dto.ErrorResponse;
import com.api.rest.application.dto.UserDTO;
import com.api.rest.domain.model.UserBank;
import com.api.rest.domain.ports.input.UserCreationUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserCreationUseCase userService;

    public UserController(UserCreationUseCase userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            UserBank user = userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("mensaje", e.getMessage()));
        }
    }


}
