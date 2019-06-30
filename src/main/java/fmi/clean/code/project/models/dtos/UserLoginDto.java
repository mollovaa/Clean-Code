package fmi.clean.code.project.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;


import static fmi.clean.code.project.helpers.Constants.INVALID_PASSWORD;
import static fmi.clean.code.project.helpers.Constants.INVALID_USERNAME;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

  @NotBlank(message = INVALID_USERNAME)
  private String username;
  @NotBlank(message = INVALID_PASSWORD)
  private String password;

}