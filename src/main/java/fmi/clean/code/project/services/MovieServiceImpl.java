package fmi.clean.code.project.services;

import fmi.clean.code.project.exceptions.MovieNotFoundException;
import fmi.clean.code.project.models.entities.Movie;
import fmi.clean.code.project.repositories.MovieRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static fmi.clean.code.project.helpers.Constants.MOVIE_WITH_ID_S_DOESN_T_EXIST;

@Service
public class MovieServiceImpl implements MovieService {

  private MovieRepository movieRepository;

  @Autowired
  public MovieServiceImpl(MovieRepository movieRepository) {
    this.movieRepository = movieRepository;
  }

  @Override
  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }

  @Override
  public Movie getMovie(Long id) {
    return movieRepository.findById(id)
        .orElseThrow(() -> new MovieNotFoundException(String.format(MOVIE_WITH_ID_S_DOESN_T_EXIST, id)));
  }
}
