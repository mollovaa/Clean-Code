package fmi.clean.code.project.models.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "message")
  private String message;
  @Column(name = "date_of_publication")
  private LocalDate dateOfPublication;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "movie_id", referencedColumnName = "id")
  private Movie movie;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "publisher_id", referencedColumnName = "id")
  private User publisher;

}
