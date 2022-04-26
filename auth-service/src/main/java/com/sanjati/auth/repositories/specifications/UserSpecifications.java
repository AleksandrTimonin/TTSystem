package com.sanjati.auth.repositories.specifications;


import com.sanjati.auth.entities.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> userEqual(String username) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username);
    }
    public static Specification<User> isActual() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(root.get("actually"));
    }



}
