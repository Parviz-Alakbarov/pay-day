package az.express.user.service.impl;

import az.express.user.error.DataMismatchException;
import az.express.user.error.UserNotFoundException;
import az.express.user.model.dto.UserDto;
import az.express.user.model.dto.UserResponseDto;
import az.express.user.model.entity.User;
import az.express.user.repository.UserRepository;
import az.express.user.mapper.UserMapper;
import az.express.user.messages.MessageSender;
import az.express.user.messages.model.Message;
import az.express.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MessageSender<String> messageSender;
    private final UserMapper userMapper;

    /**
     * Create new user and send notification
     * Did not create a state to maintain notification has been sent or not, If I would maintained will need to retry notification.
     *
     * @param dto - User information
     * @return - return created user
     */
    @Override
    public UserResponseDto save(UserDto dto) {
        Optional<User> findByEmail = userRepository.findByEmail(dto.getEmail());
        User savedUser;

        if (findByEmail.isEmpty()) {
            User userEntity = userMapper.toUserEntity(dto);
            savedUser = userRepository.save(userEntity);

            Optional.of(savedUser)
                    .map(User::getEmail)
                    .ifPresent(email -> messageSender.send(new Message<>(email)));

        } else {
            throw new DataMismatchException("Email address already exist");
        }

        return userMapper.toUserResponseDto(savedUser);
    }

    @Override
    public UserResponseDto findById(Long id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userMapper.toUserResponseDto(user);
    }
}
