package com.POS.system.service;


import com.POS.system.exception.UserException;
import com.POS.system.payload.dto.UserDto;
import com.POS.system.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signUp(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;

}
