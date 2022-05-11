package com.sanjati.core.services;



import com.sanjati.api.utils.AppFormatter;
import com.sanjati.core.converters.OrderConverter;
import com.sanjati.core.dto.FullOrderDtoWithProcessInfo;
import com.sanjati.core.dto.OrderDetailsDto;
import com.sanjati.core.dto.SuccessOrderDto;
import com.sanjati.api.exceptions.ResourceNotFoundException;
import com.sanjati.core.entities.Commit;
import com.sanjati.core.entities.ExecuteProcess;
import com.sanjati.core.entities.Order;
import com.sanjati.core.repositories.CommitsRepository;
import com.sanjati.core.repositories.OrdersRepository;
import com.sanjati.core.repositories.ProcessesRepository;
import com.sanjati.core.repositories.specifications.OrderSpecifications;
import com.sanjati.core.repositories.specifications.ProcessesSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CommitsRepository commitsRepository;
    private final ProcessesRepository processesRepository;
    private final OrderConverter orderConverter;

    //status :
    //created assigned accepted executed deferred

    @Transactional
    public SuccessOrderDto createOrder(String username, OrderDetailsDto orderDetailsDto) {
        Order order = new Order();
        order.setTitle(orderDetailsDto.getTitle());
        order.setDescription(orderDetailsDto.getDescription());
        order.setUsername(username);
        order.setStatus("CREATED");
        order.setIsActive(true);

        order = ordersRepository.save(order);





        SuccessOrderDto result = new SuccessOrderDto(order.getCreatedAt().format(AppFormatter.getFormatter()),username,order.getId());
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
    public Page<FullOrderDtoWithProcessInfo> findAllByExecutor(String oldDate, String newDate, Integer page, String executor) {
        Specification<Order> spec = Specification.where(null);

        spec = spec.and((OrderSpecifications.executorLike(executor)));
        spec = spec.and(OrderSpecifications.isActuallyOrder());


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
        Page<Order> orders = ordersRepository.findAll(spec,PageRequest.of(page-1,10));


        Page<FullOrderDtoWithProcessInfo> dtos = orders.map(o->  orderConverter.entityToFullOrderDtoWithProcessInfo(o,executor));





        return dtos;
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
    public SuccessOrderDto assignById(Long id, String assignedUsername,String username){
        Order order = ordersRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Ордер в базе не найден"));

    //created assigned accepted postponed canceled completed

        ExecuteProcess executeProcess = new ExecuteProcess();//id,order_id,executor,assigned!,accept,postopned,finished
        executeProcess.setOrder(order);
        executeProcess.setIsActive(true);
        executeProcess.setExecutor(assignedUsername);
        processesRepository.save(executeProcess);
        order.getProcesses().add(executeProcess);
        String executors;

        if(order.getExecutors()==null) executors = assignedUsername;
        else executors = order.getExecutors() + ", " + assignedUsername;
        order.setExecutors(executors);

        if(order.getStatus().equals("CREATED"))order.setStatus("ASSIGNED");
        commit("назначен исполнитель",username,order,assignedUsername);
        return new SuccessOrderDto(order.getUpdatedAt().format(AppFormatter.getFormatter()),order.getExecutors(),order.getId());
    }

    private void commit(String message, String username, Order order,String assignedUsername) {
       StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LocalDateTime.now().format(AppFormatter.getFormatter()))
                .append(" >>> ")
                .append(username)
                .append(" : ")
                .append(message)
                .append(" - ")
                .append(assignedUsername);

        Commit c = new Commit();
        c.setOrder(order);
        c.setCommit(stringBuilder.toString());
        commitsRepository.save(c);
        order.getCommits().add(c);

    }


    @Transactional
    public Page<Order> findAllFullOrders(String oldDate, String newDate, Integer page) {
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



        return  ordersRepository.findAll(spec,PageRequest.of(page-1,10));
    }
    @Transactional
    public SuccessOrderDto cancelById(Long id,String username) {
        Order order = findById(id).orElseThrow(()->new ResourceNotFoundException("Ордер в базе не найден"));
        order.setCompleted(LocalDateTime.now());
        order.setStatus("COMPLETED");
        commit( "невозможно выполнить, или не соотвествует профилю отдела",username,order,"");
        ordersRepository.save(order);
        return new SuccessOrderDto(order.getCompleted().format(AppFormatter.getFormatter()),username,id);
    }
}
