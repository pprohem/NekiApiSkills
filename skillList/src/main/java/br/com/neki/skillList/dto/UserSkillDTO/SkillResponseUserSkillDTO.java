package br.com.neki.skillList.dto.UserSkillDTO;

import br.com.neki.skillList.model.Skill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SkillResponseUserSkillDTO {

  private Long id;

  private String name;

  private String version;

  private String description;

  private String imageUrl;

  public SkillResponseUserSkillDTO (Skill s) {
    this.id = s.getId();
    this.name = s.getName();
    this.version = s.getVersion();
    this.description = s.getDescription();
    this.imageUrl = s.getImageUrl();
  }
}