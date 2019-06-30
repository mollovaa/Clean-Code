package fmi.clean.code.project.models.dtos;

import lombok.Data;

@Data
public class CommentDto {

  private Long id;
  private String message;
  private String publisherUsername;

}
