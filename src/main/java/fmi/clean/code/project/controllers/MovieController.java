package fmi.clean.code.project.controllers;

import fmi.clean.code.project.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

  private MovieService movieService;

  @Autowired
  public MovieController(MovieService movieService) {
    this.movieService = movieService;
  }

  @GetMapping(value = "/movies")
  public ResponseEntity getAllMovies() {
    return ResponseEntity.ok(movieService.getAllMovies());
  }

  @GetMapping(value = "/movies/{id}")
  public ResponseEntity getMovie(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(movieService.getMovie(id));
  }

  @GetMapping(value = "/movies/{id}/comments")
  public ResponseEntity getAllCommentsOfMovie(@PathVariable(name = "id") Long id) {
    return ResponseEntity.ok(movieService.getAllCommentsOfMovie(id));
  }
}
