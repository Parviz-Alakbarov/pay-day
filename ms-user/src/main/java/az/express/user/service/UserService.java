package az.express.user.service;

import az.express.user.model.dto.UserDto;
import az.express.user.model.dto.UserResponseDto;

public interface UserService {

    UserResponseDto save(UserDto dto);

    UserResponseDto findById(Long id);

}
