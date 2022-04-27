package com.sanjati.auth.converters;

import com.sanjati.api.auth.UserDto;
import com.sanjati.auth.configs.SecurityConfig;
import com.sanjati.auth.entities.Role;
import com.sanjati.auth.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {
    @Autowired
    SecurityConfig securityConfig;
    public UserDto modelToDto(User user){
        return new UserDto(user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getEmail(),
                user.getCompany(),
                user.getPhone(),
                user.getOffice(),
                user.getBuilding(),
                user.getUsername());
    }
    public User dtoToEntity(UserDto dto, List<Role> roles){

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(securityConfig.passwordEncoder().encode(dto.getPassword()));
        user.setActually(true);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPatronymic(dto.getPatronymic());
        user.setEmail(dto.getEmail());
        user.setCompany(dto.getCompany());
        user.setPhone(dto.getPhone());
        user.setOffice(dto.getOffice());
        user.setBuilding(dto.getBuilding());
        user.setRoles(roles);

        return user;
    }
}
