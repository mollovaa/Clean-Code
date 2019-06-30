package fmi.clean.code.project.services;

import fmi.clean.code.project.exceptions.InvalidInputException;
import fmi.clean.code.project.models.entities.Comment;
import fmi.clean.code.project.models.entities.Movie;
import fmi.clean.code.project.models.entities.User;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import static fmi.clean.code.project.helpers.Constants.INVALID_COMMENT_MESSAGE;

@Service
public class CommentServiceImpl implements CommentService {

  private MovieService movieService;
  private UserService userService;

  @Autowired
  public CommentServiceImpl(MovieService movieService, UserService userService) {
    this.movieService = movieService;
    this.userService = userService;
  }

  @Override
  public Movie commentMovie(Long movieId, Comment comment, Long publisherId) {
    Movie movie = movieService.getMovie(movieId);
    User user = userService.findById(publisherId);
    return saveComment(comment, user, movie);
  }

  private Movie saveComment(Comment comment, User user, Movie movie) throws InvalidInputException {
    this.validateComment(comment);
    this.setInitialCommentValues(comment, user, movie);
    user.getComments().add(comment);
    userService.save(user);
    movie.getComments().add(comment);
    return movieService.save(movie);
  }

  private void validateComment(Comment comment) throws InvalidInputException {
    if (StringUtils.isEmpty(comment.getMessage())) {
      throw new InvalidInputException(INVALID_COMMENT_MESSAGE);
    }
  }

  private void setInitialCommentValues(Comment comment, User user, Movie movie) {
    comment.setDateOfPublication(LocalDate.now());
    comment.setPublisher(user);
    comment.setMovie(movie);
  }

}
