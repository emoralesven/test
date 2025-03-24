package com.api.rest.service;

import com.api.rest.application.dto.UserDTO;
import com.api.rest.application.service.UserService;
import com.api.rest.domain.model.UserBank;
import com.api.rest.domain.ports.output.UserRepository;
import com.api.rest.infrastructure.config.RegexProperties;
import com.api.rest.infrastructure.security.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private RegexProperties regexProperties;

    @InjectMocks
    private UserService userService;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userDTO = new UserDTO();
        userDTO.setName("Juan Rodriguez");
        userDTO.setEmail("juan@rodriguez.cl");
        userDTO.setPassword("hunter2x");

        when(regexProperties.getEmail()).thenReturn("^[\\w._%+-]+@\\w+\\.cl$");
        when(regexProperties.getPassword()).thenReturn("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(jwtTokenUtil.generateToken(userDTO.getEmail())).thenReturn("dummy-token");

        UserBank userBank = userService.createUser(userDTO);

        assertEquals(userDTO.getEmail(), userBank.getEmail());
        assertEquals("dummy-token", userBank.getToken());
        verify(userRepository, times(1)).save(any(UserBank.class));
    }

    @Test
    void shouldThrowExceptionIfEmailAlreadyExists() {
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(new UserBank()));

        Exception exception = assertThrows(Exception.class, () -> userService.createUser(userDTO));
        assertEquals("El correo ya registrado", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfEmailIsInvalid() {
        userDTO.setEmail("invalid-email");
        Exception exception = assertThrows(Exception.class, () -> userService.createUser(userDTO));
        assertEquals("El correo no tiene un formato válido", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfPasswordIsInvalid() {
        userDTO.setPassword("short");
        Exception exception = assertThrows(Exception.class, () -> userService.createUser(userDTO));
        assertEquals("La contraseña no tiene un formato válido", exception.getMessage());
    }
}
