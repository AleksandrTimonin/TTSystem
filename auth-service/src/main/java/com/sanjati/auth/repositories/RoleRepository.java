package com.sanjati.auth.repositories;

import com.sanjati.auth.entities.Role;
import com.sanjati.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
