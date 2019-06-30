package fmi.clean.code.project.mappers;

import fmi.clean.code.project.models.dtos.MovieListDto;
import fmi.clean.code.project.models.dtos.SingleMovieDto;
import fmi.clean.code.project.models.entities.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieMapper {

  MovieListDto movieToMovieListDto(Movie movie);

  @Mapping(target = "numberOfComments", expression = "java(movie.getComments().size())")
  SingleMovieDto movieToSingleMovieDto(Movie movie);

}
