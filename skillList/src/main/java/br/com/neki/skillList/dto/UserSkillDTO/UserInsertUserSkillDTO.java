package br.com.neki.skillList.dto.UserSkillDTO;

import br.com.neki.skillList.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserInsertUserSkillDTO {
  
  @NotNull(message = "id cannot be null")
  private Long id;

  public UserInsertUserSkillDTO (User u) {
    this.id = u.getId();
  }
}
