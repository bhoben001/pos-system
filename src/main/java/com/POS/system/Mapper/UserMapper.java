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
        userDto.setBranchId((savedUser.getBranch()!=null)?savedUser.getBranch().getId():null);
        userDto.setStoreId((savedUser.getStore()!=null)?savedUser.getStore().getId():null);

        return userDto;
    }

    public static User toEntity(UserDto dto) {
        User user = new User();

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setPhone(dto.getPhone());
        user.setPassword(dto.getPassword());
        user.setLastLogin(dto.getLastLogin());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
        return user;
    }
}
