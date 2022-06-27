package az.express.notification.service.impl;

import az.express.notification.client.MsUserClient;
import az.express.notification.client.model.UserDto;
import az.express.notification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final MsUserClient msUserClient;

    @Override
    public UserDto findById(Long userId) {
        return msUserClient.findById(userId).getData();
    }
}
