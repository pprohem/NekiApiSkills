package br.com.neki.skillList.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.neki.skillList.dto.SkillDTO.SkillRequestDTO;
import br.com.neki.skillList.dto.SkillDTO.SkillResponseDTO;
import br.com.neki.skillList.exception.ApiError;
import br.com.neki.skillList.exception.SkillException;
import br.com.neki.skillList.service.SkillService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/skill")
public class SkillController {
    @Autowired
    public SkillService skillService;
    
    @GetMapping
    public ResponseEntity<List<SkillResponseDTO>> getSkills() {
       return ResponseEntity.ok(skillService.findAll());
    };


    @PostMapping
    public ResponseEntity<Object> createSkill(@Valid @RequestBody SkillRequestDTO skRequest) {
        try {
            SkillResponseDTO response = skillService.registerSkill(skRequest);
             URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                 .buildAndExpand(response.getId())
                 .toUri();
             return ResponseEntity.created(uri).body(response);
           } catch (SkillException e) {
             return ResponseEntity.unprocessableEntity()
                 .body(new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity", e.getLocalizedMessage()));
           }
    }


    @PutMapping("{id}")
    public ResponseEntity<Object> updateSkill(@PathVariable Long id, @Valid @RequestBody SkillRequestDTO skRequest) {
        try {
            SkillResponseDTO response = skillService.updateSkill(id, skRequest);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(response.getId())
            .toUri();

            return ResponseEntity.created(uri).body(response);

        } catch (SkillException e) {
            return ResponseEntity.unprocessableEntity()
                .body(new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity", e.getLocalizedMessage()));
          }
    }




    @DeleteMapping("{id}") 
    public ResponseEntity<Object> deleteSkill(@PathVariable Long id) {
        try {
            skillService.deleteSkill(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (SkillException e) {
            return ResponseEntity.unprocessableEntity()
            .body(new ApiError (HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity", e.getLocalizedMessage()));
        }
}

}
