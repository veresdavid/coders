package hu.unideb.inf.coders.repository;

import hu.unideb.inf.coders.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    SkillEntity findById(Long id);

    SkillEntity findByName(String name);

}
