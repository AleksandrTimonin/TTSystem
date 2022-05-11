package com.sanjati.core.repositories.specifications;


import com.sanjati.core.entities.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class OrderSpecifications {
    public static Specification<Order> timeGreaterOrEqualsThan(LocalDateTime time) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), time);
    }

    public static Specification<Order> timeLessThanOrEqualsThan(LocalDateTime time) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), time);
    }
    public static Specification<Order> userEqual(String username) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("username"), username);
    }
    public static Specification<Order> executorLike(String executor) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("executors"), executor);
    }
    public static Specification<Order> isActuallyOrder(){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(root.get("isActive"));
    }


}
