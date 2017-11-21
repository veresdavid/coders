package hu.unideb.inf.coders.repository;

import hu.unideb.inf.coders.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobEntity, Long> {

    JobEntity findById(Long id);

    JobEntity findByName(String name);

}
