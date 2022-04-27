package com.sanjati.auth.controllers;

import com.sanjati.api.auth.EmployersDto;
import com.sanjati.api.auth.UserDto;
import com.sanjati.auth.converters.UserConverter;
import com.sanjati.auth.entities.User;
import com.sanjati.auth.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;
    @Autowired
    private final UserConverter userConverter;

    @GetMapping("/data")
    public UserDto getFullData(@RequestHeader String username){

        return userConverter.modelToDto(userService.findActualUserByUsername(username));

    }
    @GetMapping("/employers")
    public EmployersDto getEmployers(@RequestHeader String username){

        log.warn(username);

        return userService.getAllEmployers();

    }
}
