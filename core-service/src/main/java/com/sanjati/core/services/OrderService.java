package com.sanjati.core.services;


import com.sanjati.api.core.OrderDetailsDto;
import com.sanjati.api.core.SuccessCreatedDto;
import com.sanjati.api.exceptions.ResourceNotFoundException;
import com.sanjati.core.entities.Order;
import com.sanjati.core.repositories.OrdersRepository;
import com.sanjati.core.repositories.specifications.OrderSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;



    @Transactional
    public SuccessCreatedDto createOrder(String username, OrderDetailsDto orderDetailsDto) {
        Order order = new Order();
        order.setTitle(orderDetailsDto.getTitle());
        order.setDescription(orderDetailsDto.getDescription());
        order.setUsername(username);
        order.setStatus("CREATED");

        order = ordersRepository.save(order);

        LocalDateTime date = order.getCreatedAt();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = date.format(formatter);

        SuccessCreatedDto result = new SuccessCreatedDto(formattedDateTime,order.getId());
        return result;
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }

    public Page<Order> findAllByUsername(String oldDate, String newDate,Integer page,String username) {
        Specification<Order> spec = Specification.where(null);

        spec = spec.and((OrderSpecifications.userEqual(username)));
        log.warn(username);

        if (oldDate != null) {
            LocalDateTime oldDateFormat = LocalDateTime.parse(oldDate.substring(0,22));
            spec = spec.and(OrderSpecifications.timeGreaterOrEqualsThan(oldDateFormat));
            log.warn(oldDate);
        }
        if (newDate != null) {
            LocalDateTime newDateFormat = LocalDateTime.parse(newDate.substring(0,22));
            log.warn(newDate);
            spec = spec.and(OrderSpecifications.timeLessThanOrEqualsThan(newDateFormat));
        }


        return ordersRepository.findAll(spec,PageRequest.of(page-1,10));
    }
    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }
    @Transactional
    public void changeStatus(Long id, String status){
        Order order = ordersRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order not found"));
        order.setStatus(status);

    }
}
