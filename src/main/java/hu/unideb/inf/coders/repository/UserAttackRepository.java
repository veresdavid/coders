package hu.unideb.inf.coders.repository;

import hu.unideb.inf.coders.entity.UserAttackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAttackRepository extends JpaRepository<UserAttackEntity, Long> {

    UserAttackEntity findById(Long id);

    UserAttackEntity findByName(String name);

    @Query(value = "SELECT * FROM user_attacks WHERE attacker_id = ?1 AND gained_rewards IS FALSE LIMIT 1", nativeQuery = true)
    UserAttackEntity getActiveAttack(Long userId);

}
