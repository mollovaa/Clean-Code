package fmi.clean.code.project.controllers;

import fmi.clean.code.project.models.entities.Comment;
import fmi.clean.code.project.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {

  private CommentService commentService;

  @Autowired
  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @PostMapping(value = "/movies/{id}/comment")
  public ResponseEntity commentMovie(@PathVariable(name = "id") Long movieId, @RequestBody Comment comment,
      @RequestParam(name = "publisherId") Long publisherId) {
    return ResponseEntity.ok(commentService.commentMovie(movieId, comment, publisherId));
  }

}
