package com.POS.system.service.impl;

import com.POS.system.Model.User;
import com.POS.system.config.jwt.JwtConstant;
import com.POS.system.config.jwt.JwtProvider;
import com.POS.system.exception.UserException;
import com.POS.system.repository.UserRepository;
import com.POS.system.service.UserService;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email= jwtProvider.getEmailFromToken(token);
        User user= userRepository.findByEmail(email);
        if(user==null) throw new UserException("invalid token");
        return user;
    }

    @Override
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByEmail(email);
        if(user==null) new RuntimeException("user not found");
        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user= userRepository.findByEmail(email);
        if(user==null) throw new UserException("user not found");
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user= userRepository.findById(id).get();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
