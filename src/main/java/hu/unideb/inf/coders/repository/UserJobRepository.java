package hu.unideb.inf.coders.repository;

import hu.unideb.inf.coders.entity.UserJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJobRepository extends JpaRepository<UserJobEntity, Long> {
}
