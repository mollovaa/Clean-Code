package fmi.clean.code.project.mappers;

import fmi.clean.code.project.models.dtos.CommentDto;
import fmi.clean.code.project.models.entities.Comment;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CommentMapper {

  @Mapping(target = "publisherUsername", expression = "java(comment.getPublisher().getUsername())")
  CommentDto commentToCommentDto(Comment comment);
}
