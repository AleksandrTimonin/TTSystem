package com.sanjati.auth.converters;

import com.sanjati.api.auth.UserDto;
import com.sanjati.auth.entities.Role;
import com.sanjati.auth.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {
    public static UserDto modelToDto(User user){// для скведений о себе
        return new UserDto(user.getFirstName(),
                user.getLastName(),
                user.getPatronymic(),
                user.getEmail(),
                user.getCompany(),
                user.getPhone(),
                user.getOffice(),
                user.getBuilding());
    }
    public static User registrationDtoToEntity(UserDto dto, List<Role> roles){// для регистрации
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPatronymic(dto.getPatronymic());
        user.setEmail(dto.getEmail());
        user.setCompany(dto.getCompany());
        user.setPhone(dto.getPhone());
        user.setOffice(dto.getOffice());
        user.setBuilding(dto.getBuilding());
        user.setRoles(roles);
        user.setActually(true);

        return user;
    }
}
