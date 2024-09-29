package com.sda_project.myfluffy.mapper;

import com.sda_project.myfluffy.dto.UserDto;
import com.sda_project.myfluffy.user.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user, UserDto userDto) {
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        // userDto.setLocation(user.getLocation());
        return userDto;
    }

    public static User mapToUser(UserDto userDto, User user) {
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        // user.setLocation(userDto.getLocation());
        return user;
    }

}
