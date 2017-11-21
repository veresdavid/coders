package hu.unideb.inf.coders.repository;

import hu.unideb.inf.coders.entity.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<LevelEntity, Long> {

    LevelEntity findByLevel(int level);

}
