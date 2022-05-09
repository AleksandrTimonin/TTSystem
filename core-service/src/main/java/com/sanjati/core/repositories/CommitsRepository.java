package com.sanjati.core.repositories;

import com.sanjati.core.entities.Commit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitsRepository extends JpaRepository<Commit, Long>, JpaSpecificationExecutor<Commit> {
}
