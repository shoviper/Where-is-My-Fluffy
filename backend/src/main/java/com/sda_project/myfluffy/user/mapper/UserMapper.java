package com.sda_project.myfluffy.user.mapper;

import com.sda_project.myfluffy.user.dto.UserDto;
import com.sda_project.myfluffy.user.model.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user, UserDto userDto) {
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setUserImage(user.getUserImage());
        userDto.setBalance(user.getBalance());
        return userDto;
    }

    public static User mapToUser(UserDto userDto, User user) {
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setUserImage(userDto.getUserImage());
        user.setBalance(userDto.getBalance());
        return user;
    }

}
