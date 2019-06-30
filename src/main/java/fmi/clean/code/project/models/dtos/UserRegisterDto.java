package fmi.clean.code.project.models.dtos;

import fmi.clean.code.project.annotations.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


import static fmi.clean.code.project.helpers.Constants.INVALID_CONFIRM_PASSWORD;
import static fmi.clean.code.project.helpers.Constants.INVALID_EMAIL;
import static fmi.clean.code.project.helpers.Constants.INVALID_USERNAME;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

  @NotBlank(message = INVALID_EMAIL)
  @Email
  private String email;
  @NotBlank(message = INVALID_USERNAME)
  private String username;
  @ValidPassword
  private String password;
  @NotBlank(message = INVALID_CONFIRM_PASSWORD)
  private String confirmPassword;
}
