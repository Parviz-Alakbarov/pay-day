package az.express.user.mapper;

import az.express.user.model.dto.UserDto;
import az.express.user.model.dto.UserResponseDto;
import az.express.user.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public abstract class UserMapper {

    public abstract User toUserEntity(UserDto dto);

    public abstract UserDto toUserDto(User dto);

    public abstract UserResponseDto toUserResponseDto(User dto);

}
