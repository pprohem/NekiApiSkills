package br.com.neki.skillList.dto.UserSkillDTO;

import br.com.neki.skillList.model.Skill;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SkillInsertUserSkillDTO {
  
  @NotNull(message = "id cannot be null")
  private Long id;

  public SkillInsertUserSkillDTO (Skill s) {
    this.id = s.getId();
  }
}
