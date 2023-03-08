package br.com.neki.skillList.dto.SkillDTO.UserDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.neki.skillList.dto.UserSkillDTO.UserSkillResponseUserDTO;
import br.com.neki.skillList.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserResponseDTO {

  private Long id;

  private String username;

  private LocalDate lastLoginDate;

  private List<UserSkillResponseUserDTO> userSkill = new ArrayList<>();

  public UserResponseDTO(User u) {
    this.id = u.getId();
    this.username = u.getUsername();
    this.lastLoginDate = u.getLastLoginDate();
    u.getUserSkill().forEach(user -> userSkill.add(new UserSkillResponseUserDTO(user)));
  }
}
