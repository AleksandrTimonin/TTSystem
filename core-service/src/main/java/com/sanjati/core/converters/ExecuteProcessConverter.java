package com.sanjati.core.converters;



import com.sanjati.api.utils.AppFormatter;
import com.sanjati.core.dto.ExecuteProcessDto;
import com.sanjati.core.entities.ExecuteProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExecuteProcessConverter {
    public ExecuteProcessDto entityToDto(ExecuteProcess process) {
        ExecuteProcessDto dto = new ExecuteProcessDto();
        dto.setExecutor(process.getExecutor());
        dto.setAssignedAt(process.getAssignedAt().format(AppFormatter.getFormatter()));
        if(process.getAcceptedAt()!=null)dto.setAcceptedAt(process.getAcceptedAt().format(AppFormatter.getFormatter()));
        else dto.setAcceptedAt("пока не принят");
        if(process.getPostponedAt()!=null)dto.setPostponedAt(process.getPostponedAt().format(AppFormatter.getFormatter()));
        else dto.setPostponedAt("пока не завершён");

        return dto;
    }
}
