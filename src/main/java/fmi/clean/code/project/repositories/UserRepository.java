package fmi.clean.code.project.repositories;

import fmi.clean.code.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
