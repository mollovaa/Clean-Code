package fmi.clean.code.project.models.dtos;

import lombok.Data;

@Data
public class MovieListDto {

  private Long id;
  private String title;
  private Integer year;
  private String posterUrl;

}
