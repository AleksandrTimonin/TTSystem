package com.sanjati.core.controllers;

import com.sanjati.api.auth.UserDto;
import com.sanjati.core.dto.RolesDto;
import com.sanjati.core.dto.UserDataRequest;
import com.sanjati.core.integrations.AuthServiceIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UsersController {
    private final AuthServiceIntegration authServiceIntegration;
    @GetMapping("/roles")
    public RolesDto getRoles(@RequestHeader String username, @RequestHeader String role) {

        RolesDto roles = new RolesDto(role);

        return roles;
    }
    @PostMapping("/info")
    public UserDto loadUser(@RequestHeader String username, @RequestHeader String role, @RequestBody UserDataRequest user) {
        return authServiceIntegration.getUser(user.getUser());
    }
    @GetMapping("/personal")
    public UserDto getPersonal(@RequestHeader String username, @RequestHeader String role) {
        return authServiceIntegration.getUser(username);
    }
}
