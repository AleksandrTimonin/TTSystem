package com.sanjati.core.controllers;

import com.sanjati.api.auth.EmployersDto;
import com.sanjati.api.auth.UserDto;
import com.sanjati.core.integrations.AuthServiceIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/employers")
@RequiredArgsConstructor
public class EmployersController {
    private final AuthServiceIntegration authServiceIntegration;
    @GetMapping
    public EmployersDto getEmployers(@RequestHeader String username, @RequestHeader String role) {
        return authServiceIntegration.getEmployers(username);
    }
}
