package fmi.clean.code.project;

import fmi.clean.code.project.exceptions.MovieNotFoundException;
import fmi.clean.code.project.mappers.MovieMapper;
import fmi.clean.code.project.models.dtos.MovieListDto;
import fmi.clean.code.project.models.dtos.SingleMovieDto;
import fmi.clean.code.project.models.entities.Movie;
import fmi.clean.code.project.repositories.MovieRepository;
import fmi.clean.code.project.services.MovieServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

  @Mock
  private MovieRepository movieRepository;
  @Mock
  private MovieMapper movieMapper;

  @InjectMocks
  private MovieServiceImpl movieService;


  @Test
  public void getAll_NoMovies() {
    when(movieRepository.findAll()).thenReturn(new ArrayList<>());
    when(movieMapper.movieToMovieListDto(new Movie())).thenReturn(new MovieListDto());

    List<MovieListDto> result = movieService.getAllMovies();

    Assert.assertEquals(0, result.size());
    verify(movieRepository).findAll();
  }

  @Test(expected = MovieNotFoundException.class)
  public void getMovie_notExistingMovie() {
    when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());

    movieService.getMovieById(anyLong());

    verify(movieRepository).findById(anyLong());
  }

  @Test
  public void getMovie_existingMovie() {
    when(movieRepository.findById(anyLong())).thenReturn(Optional.of(new Movie()));

    Movie result = movieService.getMovieById(anyLong());

    Assert.assertNotNull(result);
    verify(movieRepository).findById(anyLong());

  }

}
