package com.api.rest.domain.ports.input;


import com.api.rest.application.dto.UserDTO;
import com.api.rest.domain.model.UserBank;

public interface UserCreationUseCase {
    UserBank createUser(UserDTO userDTO) throws Exception;
}
