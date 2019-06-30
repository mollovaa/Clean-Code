package fmi.clean.code.project.models.entities;


import fmi.clean.code.project.models.entities.Comment;
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
import lombok.NoArgsConstructor;
import org.mapstruct.Named;

@NoArgsConstructor
@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "email", nullable = false)
  private String email;
  @Column(name = "username", nullable = false)
  private String username;
  @Column(name = "password", nullable = false)
  private String password;

  @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
  private List<Comment> comments;

  public User(String password) {
    this.password = password;
  }
}