package fmi.clean.code.project.services;

import fmi.clean.code.project.exceptions.MovieNotFoundException;
import fmi.clean.code.project.mappers.CommentMapper;
import fmi.clean.code.project.mappers.MovieMapper;
import fmi.clean.code.project.models.dtos.CommentDto;
import fmi.clean.code.project.models.dtos.MovieListDto;
import fmi.clean.code.project.models.dtos.SingleMovieDto;
import fmi.clean.code.project.models.entities.Movie;
import fmi.clean.code.project.repositories.MovieRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static fmi.clean.code.project.helpers.Constants.MOVIE_WITH_ID_S_DOESN_T_EXIST;

@Service
public class MovieServiceImpl implements MovieService {

  private MovieRepository movieRepository;
  private MovieMapper movieMapper;
  private CommentMapper commentMapper;

  @Autowired
  public MovieServiceImpl(MovieRepository movieRepository, MovieMapper movieMapper, CommentMapper commentMapper) {
    this.movieRepository = movieRepository;
    this.movieMapper = movieMapper;
    this.commentMapper = commentMapper;
  }

  @Override
  public List<MovieListDto> getAllMovies() {
    return movieRepository.findAll()
        .stream()
        .map(movieMapper::movieToMovieListDto)
        .collect(Collectors.toList());
  }

  @Override
  public SingleMovieDto getMovie(Long id) {
    return movieMapper.movieToSingleMovieDto(getMovieById(id));
  }

  @Override
  public List<CommentDto> getAllCommentsOfMovie(Long id) {
    Movie movie = this.getMovieById(id);
    return movie.getComments()
        .stream()
        .map(commentMapper::commentToCommentDto)
        .collect(Collectors.toList());
  }

  @Override
  public Movie save(Movie movie) {
    return movieRepository.save(movie);
  }

  @Override
  public Movie getMovieById(Long id) {
    return movieRepository.findById(id)
        .orElseThrow(() -> new MovieNotFoundException(String.format(MOVIE_WITH_ID_S_DOESN_T_EXIST, id)));
  }

}
