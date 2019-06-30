package fmi.clean.code.project.models.entities;


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
  @Column(name = "comment_id")
  private Long commentId;
  @Column(name = "message")
  private String message;
  @Column(name = "date_of_publication")
  private LocalDate dateOfPublication;

  @ManyToOne
  @JoinColumn(name = "movie_id", referencedColumnName = "id")
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "publisher_id", referencedColumnName = "id")
  private User publisher;

}
