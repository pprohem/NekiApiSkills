package br.com.neki.skillList.dto.UserSkillDTO;

import br.com.neki.skillList.model.UserSkill;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserSkillInsertDTO {
  
  @NotNull(message = "knowledge Level must not be null")
  private Integer knowledgeLevel;

  @Valid
  @NotNull(message = "user Level must not be null")
  private UserInsertUserSkillDTO user;

  @Valid
  @NotNull(message = "skill Level must not be null")
  private SkillInsertUserSkillDTO skill;

  public UserSkillInsertDTO (UserSkill u) {
    this.knowledgeLevel = u.getKnowledgeLevel();
    this.user = new UserInsertUserSkillDTO(u.getUser());
    this.skill = new SkillInsertUserSkillDTO(u.getSkill());
  }
}