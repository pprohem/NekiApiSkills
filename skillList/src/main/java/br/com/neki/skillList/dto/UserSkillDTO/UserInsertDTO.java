package br.com.neki.skillList.dto.UserSkillDTO;

import br.com.neki.skillList.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserInsertDTO {

  @NotBlank(message = "A non-null username must be entered.")
  private String username;

  @NotBlank(message = "A non-null user password must be entered.")
  @Size(min = 8, message = "Password must be greater than 8 characters")
  private String password;

  public UserInsertDTO(User u) {
    this.username = u.getUsername();
    this.password = u.getPassword();
  }
}


