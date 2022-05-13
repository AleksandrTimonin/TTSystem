package com.sanjati.core.services;

import com.sanjati.api.utils.AppFormatter;
import com.sanjati.core.converters.ExecuteProcessConverter;
import com.sanjati.core.dto.ExecuteProcessDto;
import com.sanjati.core.dto.SuccessOrderDto;
import com.sanjati.core.entities.Commit;
import com.sanjati.core.entities.ExecuteProcess;
import com.sanjati.core.entities.Order;
import com.sanjati.core.repositories.CommitsRepository;
import com.sanjati.core.repositories.ProcessesRepository;
import com.sanjati.core.repositories.specifications.ProcessesSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessesService {
    private final ProcessesRepository processesRepository;
    private final ExecuteProcessConverter converter;
    private final CommitsRepository commitsRepository;

    public List<ExecuteProcessDto> findAllActuallyProcessesBySpec(String executors,Long id) {
        String[] users = executors.split(", ");

        Specification<ExecuteProcess> spec = Specification.where(null);
        spec = spec.and(ProcessesSpecifications.idEqual(id));
        List<ExecuteProcessDto> result =processesRepository.findAll(spec).stream().map(converter::entityToDto).collect(Collectors.toList());
        return result;


    }
    @Transactional
    public SuccessOrderDto acceptByIdAndUsername(String username, Long id) {
        ExecuteProcess entity = getExecuteProcess(username, id);
        entity.setAcceptedAt(LocalDateTime.now());
        processesRepository.save(entity);
        commit("приступил",username,entity.getOrder());
        SuccessOrderDto result = new SuccessOrderDto(entity.getAcceptedAt().format(AppFormatter.getFormatter()),username,id);
        return result;
    }

    private ExecuteProcess getExecuteProcess(String username, Long id) {
        Specification<ExecuteProcess> spec = Specification.where(null);
        spec = spec.and(ProcessesSpecifications.userEqual(username));
        spec = spec.and(ProcessesSpecifications.idEqual(id));
        spec = spec.and(ProcessesSpecifications.isActive());
        List<ExecuteProcess> process = processesRepository.findAll(spec);
        ExecuteProcess entity = process.get(0);
        return entity;
    }
    @Transactional
    public SuccessOrderDto postponeByIdAndUsername(String username,Long id) {
        ExecuteProcess entity = getExecuteProcess(username, id);
        entity.setFinishedAt(LocalDateTime.now());
        entity.setIsActive(false);
        processesRepository.save(entity);

        ExecuteProcess reopenedProcess = new ExecuteProcess();
        reopenedProcess.setOrder(entity.getOrder());
        reopenedProcess.setAssignedAt(entity.getAssignedAt());
        reopenedProcess.setIsActive(true);
        reopenedProcess.setOnConfirm(false);
        reopenedProcess.setExecutor(entity.getExecutor());
        processesRepository.save(reopenedProcess);
        commit("отложил",username,entity.getOrder());

        SuccessOrderDto result = new SuccessOrderDto(entity.getFinishedAt().format(AppFormatter.getFormatter()),username,id);
        return result;
    }
    @Transactional
    public SuccessOrderDto finishedByIdAndUsername(String username,Long id,String message) {
        ExecuteProcess entity = getExecuteProcess(username, id);
        entity.setFinishedAt(LocalDateTime.now());
        entity.setOnConfirm(true);
        processesRepository.save(entity);
        commit("отчитался",username,entity.getOrder());
        commit(message,username,entity.getOrder());



        SuccessOrderDto result = new SuccessOrderDto(entity.getFinishedAt().format(AppFormatter.getFormatter()),username,id);
        return result;
    }
    private void commit(String message, String username, Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(LocalDateTime.now().format(AppFormatter.getFormatter()))
                .append(" >>> ")
                .append(username)
                .append(" : ")
                .append(message);


        Commit c = new Commit();
        c.setOrder(order);
        c.setCommit(stringBuilder.toString());
        commitsRepository.save(c);
        order.getCommits().add(c);

    }
}
