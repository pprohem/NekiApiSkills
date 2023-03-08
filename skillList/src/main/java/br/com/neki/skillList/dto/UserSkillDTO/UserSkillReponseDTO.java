package br.com.neki.skillList.dto.UserSkillDTO;


import java.time.LocalDate;

import br.com.neki.skillList.dto.SkillDTO.UserDTO.UserResponseUserSkillDTO;
import br.com.neki.skillList.model.UserSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserSkillReponseDTO {
  
  private Long id;

  private Integer knowledgeLevel;

  private LocalDate createdAt;

  private LocalDate updatedAt;

  private SkillResponseUserSkillDTO skill;

  private UserResponseUserSkillDTO user;

  public UserSkillReponseDTO (UserSkill u) {
    this.id = u.getId();
    this.knowledgeLevel = u.getKnowledgeLevel();
    this.createdAt = u.getCreatedAt();
    this.updatedAt = u.getUpdatedAt();
    this.skill = new SkillResponseUserSkillDTO(u.getSkill());
    this.user = new UserResponseUserSkillDTO(u.getUser());
  }
}
