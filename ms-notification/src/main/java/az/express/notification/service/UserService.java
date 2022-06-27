package az.express.notification.service;

import az.express.notification.client.model.UserDto;

public interface UserService {

    UserDto findById(Long userId);
}
