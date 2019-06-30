package fmi.clean.code.project.repositories;

import fmi.clean.code.project.models.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
