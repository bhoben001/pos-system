package com.POS.system.service;

import com.POS.system.Model.User;
import com.POS.system.exception.UserException;

import java.util.List;

public interface UserService {
    User getUserFromJwtToken(String token) throws UserException;
    User getCurrentUser();
    User getUserByEmail(String email) throws UserException;
    User getUserById(Long id);
    List<User> getAllUsers();

}
