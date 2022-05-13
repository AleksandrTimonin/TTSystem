package com.sanjati.core.repositories.specifications;

import com.sanjati.core.entities.ExecuteProcess;
import org.springframework.data.jpa.domain.Specification;

public class ProcessesSpecifications {
    public static Specification<ExecuteProcess> userEqual(String username) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("executor"), username);
    }
    public static Specification<ExecuteProcess> idEqual(Long id) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("order"), id);
    }
    public static Specification<ExecuteProcess> isActive() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(root.get("is_active"));
    }
}
