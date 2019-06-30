package fmi.clean.code.project;

import fmi.clean.code.project.exceptions.InvalidInputException;
import fmi.clean.code.project.exceptions.MovieNotFoundException;
import fmi.clean.code.project.exceptions.UserNotFoundException;
import fmi.clean.code.project.models.entities.Comment;
import fmi.clean.code.project.models.entities.Movie;
import fmi.clean.code.project.models.entities.User;
import fmi.clean.code.project.services.CommentServiceImpl;
import fmi.clean.code.project.services.MovieService;
import fmi.clean.code.project.services.UserService;
import java.time.LocalDate;
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
public class CommentServiceTest {

  private Long movieId = 1L;
  private Long userId = 1L;

  @Mock
  private MovieService movieService;

  @Mock
  private UserService userService;

  @InjectMocks
  private CommentServiceImpl commentService;

  @Test(expected = MovieNotFoundException.class)
  public void commentMovie_invalidMovieId() {
    when(movieService.getMovieById(anyLong())).thenThrow(MovieNotFoundException.class);

    commentService.commentMovie(movieId, new Comment(), anyLong());

    verify(movieService).getMovieById(anyLong());
  }

  @Test(expected = UserNotFoundException.class)
  public void commentMovie_invalidPublisherId() {
    when(movieService.getMovieById(anyLong())).thenReturn(new Movie());
    when(userService.getById(anyLong())).thenThrow(UserNotFoundException.class);

    commentService.commentMovie(movieId, new Comment(), userId);

    verify(movieService).getMovieById(anyLong());
    verify(userService).getById(anyLong());
  }

  @Test(expected = InvalidInputException.class)
  public void commentVideo_invalidCommentMessage() {
    when(movieService.getMovieById(anyLong())).thenReturn(new Movie());
    when(userService.getById(anyLong())).thenReturn(new User());

    commentService.commentMovie(movieId, new Comment(), userId);

    verify(movieService).getMovieById(anyLong());
    verify(userService).getById(anyLong());
  }

  @Test
  public void commentVideo_successfullyCommented() {
    Movie movie = new Movie();
    User user = new User();
    when(movieService.getMovieById(anyLong())).thenReturn(movie);
    when(userService.getById(anyLong())).thenReturn(user);

    Comment comment = new Comment("message");
    commentService.commentMovie(movieId, comment, userId);

    verify(movieService).getMovieById(anyLong());
    verify(userService).getById(anyLong());
    verify(movieService).save(movie);

    Assert.assertEquals(LocalDate.now(), comment.getDateOfPublication());
    Assert.assertNotEquals(0, user.getComments().size());
    Assert.assertNotEquals(0, movie.getComments().size());

  }


}
