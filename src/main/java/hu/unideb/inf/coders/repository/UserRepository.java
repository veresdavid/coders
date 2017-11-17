package hu.unideb.inf.coders.repository;

import hu.unideb.inf.coders.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findById(Long id);

	UserEntity findByName(String name);

	UserEntity findByEmail(String email);

}
