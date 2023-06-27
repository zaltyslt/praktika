package lt.ks.vtmc.serviceapi.mapper;

import lt.ks.vtmc.serviceapi.user.User;
import lt.ks.vtmc.serviceapi.user.UserDto;

public interface UserMapper {

    UserDto toUserDto(User user);
}