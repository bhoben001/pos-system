package com.POS.system.service.impl;

import com.POS.system.Domain.UserRole;
import com.POS.system.Mapper.UserMapper;
import com.POS.system.Model.User;
import com.POS.system.config.JwtProvider;
import com.POS.system.exception.UserException;
import com.POS.system.payload.dto.UserDto;
import com.POS.system.payload.response.AuthResponse;
import com.POS.system.repository.UserRepository;
import com.POS.system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserDetailsServiceImpl customUserImpl;

    @Override
    public AuthResponse signUp(UserDto userDto) throws UserException {

        User user=userRepository.findByEmail(userDto.getEmail());
        if(user!=null) throw new UserException("email already registered");

        if(userDto.getRole().equals(UserRole.ROLE_ADMIN))throw new UserException("role admin is not allowed");

        User newUser=new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setFullName(userDto.getFullName());
        newUser.setPhone(userDto.getPhone());
        newUser.setLastLogin(userDto.getLastLogin());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        User savedUser=userRepository.save(newUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(userDto.getEmail(),userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("registered successfully");
        authResponse.setUser(UserMapper.toDto(savedUser));


        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) throws UserException {

        Authentication authentication=autheticate(userDto.getEmail(),userDto.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Collection<?extends GrantedAuthority> authorities=authentication.getAuthorities();
        String role=authorities.iterator().next().getAuthority();
        String jwt=jwtProvider.generateToken(authentication);
        User user=userRepository.findByEmail(userDto.getEmail());
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("login successfully");
        authResponse.setUser(UserMapper.toDto(user));
        return authResponse;
    }

    private Authentication autheticate (String email, String password) throws UserException {
        UserDetails userDetails = customUserImpl.loadUserByUsername(email);
        if(userDetails==null) throw new UserException("email id doesn't exist"+email);
        if(!passwordEncoder.matches(password,userDetails.getPassword()))throw new UserException("wrong password");
        return new  UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
