package fmi.clean.code.project.repositories;

import fmi.clean.code.project.models.entities.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Optional<User> findByUsername(String username);
}
