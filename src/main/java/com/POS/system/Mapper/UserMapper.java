package com.POS.system.Mapper;

import com.POS.system.Model.User;
import com.POS.system.payload.dto.UserDto;

public class UserMapper {
    public static UserDto toDto(User savedUser){

        UserDto userDto = new UserDto();

        userDto.setId(savedUser.getId());
        userDto.setFullName(savedUser.getFullName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setRole(savedUser.getRole());
        userDto.setPhone(savedUser.getPhone());
        userDto.setLastLogin(savedUser.getLastLogin());
        userDto.setCreatedAt(savedUser.getCreatedAt());
        userDto.setUpdatedAt(savedUser.getUpdatedAt());

        return userDto;
    }
}
