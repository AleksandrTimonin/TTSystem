package com.sanjati.core.services;

import com.sanjati.core.converters.ExecuteProcessConverter;
import com.sanjati.core.dto.ExecuteProcessDto;
import com.sanjati.core.entities.ExecuteProcess;
import com.sanjati.core.entities.Order;
import com.sanjati.core.repositories.ProcessesRepository;
import com.sanjati.core.repositories.specifications.ProcessesSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class ProcessesService {
    private final ProcessesRepository processesRepository;
    private final ExecuteProcessConverter converter;

    public List<ExecuteProcessDto> findAllActuallyProcessesBySpec(String executors,Long id) {
        String[] users = executors.split(", ");

        Specification<ExecuteProcess> spec = Specification.where(null);
//        for (String user : users){
//            spec = spec.and(ProcessesSpecifications.userEqual(user));
//        }
        log.warn(id.toString());
        spec = spec.and(ProcessesSpecifications.idEqual(id));
        List<ExecuteProcessDto> result =processesRepository.findAll(spec).stream().map(converter::entityToDto).collect(Collectors.toList());
        log.warn(result.toString());
        return result;


    }
}
