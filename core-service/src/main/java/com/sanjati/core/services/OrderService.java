package com.sanjati.core.services;



import com.sanjati.core.dto.OrderDetailsDto;
import com.sanjati.core.dto.SuccessOrderDto;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    //status :
    //created assigned accepted executed deferred

    @Transactional
    public SuccessOrderDto createOrder(String username, OrderDetailsDto orderDetailsDto) {
        Order order = new Order();
        order.setTitle(orderDetailsDto.getTitle());
        order.setDescription(orderDetailsDto.getDescription());
        order.setUsername(username);
        order.setStatus("CREATED");

        order = ordersRepository.save(order);

        LocalDateTime date = order.getCreatedAt();

        String formattedDateTime = date.format(formatter);

        SuccessOrderDto result = new SuccessOrderDto(formattedDateTime,order.getId());
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
    @Transactional
    public SuccessOrderDto updateStatusById(Long id, String status,String username){
        Order order = ordersRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Ордер в базе не найден"));


         switch (status){
             case ("ASSIGNED")://назначен
                 order.setAssignment( LocalDateTime.now());
                 order.setExecutor(username);
                 break;
             case ("ACCEPTED")://принят
                 order.setStartProgress( LocalDateTime.now());
                 break;
             case ("EXECUTED")://закрыт
             case ("DEFERRED"):
                 order.setExecuted( LocalDateTime.now());
                 break;
             default: throw new IllegalArgumentException();
         }
         order.setStatus(status);

         order = ordersRepository.save(order);

         return new SuccessOrderDto(order.getUpdatedAt().format(formatter),order.getId());
    }

    public Page<Order> findAllOrders(String oldDate, String newDate, Integer page) {
        Specification<Order> spec = Specification.where(null);



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
}
