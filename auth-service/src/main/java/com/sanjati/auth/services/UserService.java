package com.sanjati.auth.services;


import com.sanjati.api.auth.SuccessUserCreatedDto;
import com.sanjati.api.auth.UserDto;
import com.sanjati.api.exceptions.ResourceNotFoundException;
import com.sanjati.auth.converters.UserConverter;
import com.sanjati.auth.entities.Role;
import com.sanjati.auth.entities.User;
import com.sanjati.auth.repositories.RoleRepository;
import com.sanjati.auth.repositories.UserRepository;
import com.sanjati.auth.repositories.specifications.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    @Autowired
    private final UserConverter userConverter;


    public User findActualUserByUsername(String username) {
        Specification<User> spec = Specification.where(null);
        spec = spec.and((UserSpecifications.isActual()));
        spec = spec.and((UserSpecifications.userEqual(username)));
        List<User> user = userRepository.findAll(spec);
        if(user.size()!=1) throw new ResourceNotFoundException("Юзер отсуствует либо удалён");
        return user.get(0);

    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findActualUserByUsername(username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    @Transactional
    public SuccessUserCreatedDto createNewUser(UserDto userDto){
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.getById(1L));
        User user = userRepository.save(userConverter.dtoToEntity(userDto,roles));

        return new SuccessUserCreatedDto(user.getUsername(), user.getCreatedAt().format(formatter), user.getId());

    }

}