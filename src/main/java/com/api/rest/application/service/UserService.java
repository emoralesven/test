package com.api.rest.application.service;

import com.api.rest.application.dto.UserDTO;
import com.api.rest.infrastructure.config.RegexProperties;
import com.api.rest.domain.model.UserBank;
import com.api.rest.domain.ports.input.UserCreationUseCase;
import com.api.rest.domain.ports.output.UserRepository;
import com.api.rest.infrastructure.security.JwtTokenUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserCreationUseCase {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final RegexProperties regexProperties;

    @Override
    @Transactional
    public UserBank createUser(UserDTO userDTO) throws Exception {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new Exception("El correo ya registrado");
        }

        if (!userDTO.getEmail().matches(regexProperties.getEmail())) {
            throw new Exception("El correo no tiene un formato válido");
        }

        if (!userDTO.getPassword().matches(regexProperties.getPassword())) {
            throw new Exception("La contraseña no tiene un formato válido");
        }

        UserBank user = new UserBank();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        String token = jwtTokenUtil.generateToken(userDTO.getEmail());
        user.setToken(token);

        userRepository.save(user);

        return user;
    }
}
