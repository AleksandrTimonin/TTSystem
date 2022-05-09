package com.sanjati.core.repositories;


import com.sanjati.core.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    @Query("select o from Order o where o.username = ?1")
    List<Order> findAllByUsername(String username);
//    @Query("update Order o set o.status = ?1 where o.id = ?2")
//    void updateStatus(String status,Long id);
//    @Query("update Order o where o.executor = ?1 where o.id = ?2")
//    void updateExecutor(String executor,Long id);

//    @Transactional
//    @Modifying
//    @Query("update Order o set o.status = ?1 where o.id = ?2")
//    int updateStatusByIdEquals(String status, Long id);
}
