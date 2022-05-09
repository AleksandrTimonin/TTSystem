package com.sanjati.core.repositories;

import com.sanjati.core.entities.ExecuteProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessesRepository extends JpaRepository<ExecuteProcess, Long>, JpaSpecificationExecutor<ExecuteProcess> {
}
