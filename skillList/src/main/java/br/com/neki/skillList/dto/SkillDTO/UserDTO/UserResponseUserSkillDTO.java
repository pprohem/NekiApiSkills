package br.com.neki.skillList.dto.SkillDTO.UserDTO;

import java.time.LocalDate;

import br.com.neki.skillList.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserResponseUserSkillDTO {

  private Long id;

  private String username;

  private LocalDate lastLoginDate;

  public UserResponseUserSkillDTO(User u) {
    this.id = u.getId();
    this.username = u.getUsername();
    this.lastLoginDate = u.getLastLoginDate();
  }
}
