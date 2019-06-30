package fmi.clean.code.project.models.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import fmi.clean.code.project.models.entities.Comment;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "movies")
@Data
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "title")
  private String title;
  @Column(name = "description", length = 5000)
  private String description;
  @Column(name = "rating")
  private Double rating;
  @Column(name = "year")
  private Integer year;
  @Column(name = "director")
  private String director;
  @Column(name = "writer")
  private String writer;
  @Column(name = "duration")
  private Long duration;
  @Column(name = "imdb_id")
  private String imdbId;
  @JsonFormat(pattern = "yyyy-MM-dd")
  @Column(name = "release_date")
  private Timestamp releaseDate;
  @Column(name = "poster_url")
  private String posterUrl;

  @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
  private List<Comment> comments;

}