package az.express.user.controller;

import az.express.user.model.dto.UserDto;
import az.express.user.model.dto.UserResponseDto;
import az.express.user.error.model.RestResponse;
import az.express.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public RestResponse<UserResponseDto> save(@RequestBody UserDto dto) {
        return new RestResponse<>(userService.save(dto));
    }

    @GetMapping(params = "userId")
    public RestResponse<UserResponseDto> getUser(@RequestParam("userId") Long userId) {
        return new RestResponse<>(userService.findById(userId));
    }

}
