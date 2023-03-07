package br.com.neki.skillList.dto.SkillDTO;

import br.com.neki.skillList.model.Skill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SkillRequestDTO {
    private String name;

    private String version; 

    private String description;

    private String imageUrl;


    public SkillRequestDTO(Skill skill) {
        this.name = skill.getName();
        this.version = skill.getVersion();
        this.description = skill.getDescription();
        this.imageUrl = skill.getImageUrl();
    }
}
