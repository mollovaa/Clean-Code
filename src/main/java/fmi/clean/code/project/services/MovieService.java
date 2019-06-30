package fmi.clean.code.project.services;

import fmi.clean.code.project.models.dtos.CommentDto;
import fmi.clean.code.project.models.dtos.MovieListDto;
import fmi.clean.code.project.models.dtos.SingleMovieDto;
import fmi.clean.code.project.models.entities.Movie;
import java.util.List;

public interface MovieService {

  List<MovieListDto> getAllMovies();

  SingleMovieDto getMovie(Long id);

  Movie getMovieById(Long id);

  Movie save(Movie movie);

  List<CommentDto> getAllCommentsOfMovie(Long id);
}
