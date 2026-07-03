package com.POS.system.controller;

import com.POS.system.Mapper.UserMapper;
import com.POS.system.Model.User;
import com.POS.system.exception.UserException;
import com.POS.system.payload.dto.UserDto;
import com.POS.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    private ResponseEntity<UserDto> getUserProfile(@RequestHeader("Authorization") String token) throws UserException {
        User user=userService.getUserFromJwtToken(token);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    //    HAS ROLE OR AUTHORISATION CHECK NEED TO BE DONE
    @GetMapping("/{id}")
    private ResponseEntity<UserDto> getUserById(@RequestHeader("Authorization") String token,
                                                @PathVariable Long id) throws UserException {
        User user=userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
}
