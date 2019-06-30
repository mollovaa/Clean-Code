package fmi.clean.code.project.services;

import fmi.clean.code.project.models.entities.Movie;
import java.util.List;

public interface MovieService {

  List<Movie> getAllMovies();

  Movie getMovie(Long id);

  Movie save(Movie movie);
}
