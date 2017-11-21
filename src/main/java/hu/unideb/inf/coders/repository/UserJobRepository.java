package hu.unideb.inf.coders.repository;

import hu.unideb.inf.coders.entity.UserJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJobRepository extends JpaRepository<UserJobEntity, Long> {

	@Query(value = "SELECT * FROM user_jobs WHERE user_id = ?1 AND gained_rewards IS FALSE LIMIT 1", nativeQuery = true)
	UserJobEntity getActiveJob(Long userId);

}
