package fmi.clean.code.project.services;

import fmi.clean.code.project.models.entities.Comment;
import fmi.clean.code.project.models.entities.Movie;

public interface CommentService {

  Movie commentMovie(Long movieId, Comment comment, Long publisherId);
}
