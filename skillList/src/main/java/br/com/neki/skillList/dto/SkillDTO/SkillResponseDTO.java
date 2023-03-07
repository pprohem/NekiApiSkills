package br.com.neki.skillList.dto.SkillDTO;

import br.com.neki.skillList.model.Skill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SkillResponseDTO {
    
    private Long id;

    private String name;

    private String version; 

    private String description;

    private String imageUrl;


    public SkillResponseDTO(Skill skill) {
        this.id = skill.getId();
        this.name = skill.getName();
        this.version = skill.getVersion();
        this.description = skill.getDescription();
        this.imageUrl = skill.getImageUrl();
    }
}
