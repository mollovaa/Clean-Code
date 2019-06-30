package fmi.clean.code.project.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class SingleMovieDto {

  private Long id;
  private String title;
  private String description;
  private Double rating;
  private Integer year;
  private String director;
  private String writer;
  private Long duration;
  private String imdbId;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Timestamp releaseDate;
  private String posterUrl;
  private Integer numberOfComments;
}
