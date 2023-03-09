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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/skill")
@Tag(name = "Skills", description = "Programming skills")
public class SkillController {
    @Autowired
    public SkillService skillService;
    
    @GetMapping
    @Operation(summary = "Get All Skills", description = "Get All", responses = {
        @ApiResponse(responseCode = "200", description = "Successfully Get all Skills!"
       ),
        @ApiResponse(responseCode = "400", ref = "BadRequest"),
        @ApiResponse(responseCode = "401", ref = "badcredentials"),
        @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
        @ApiResponse(responseCode = "500", ref = "internalServerError")
})
    public ResponseEntity<List<SkillResponseDTO>> getSkills() {
       return ResponseEntity.ok(skillService.findAll());
    };


    @PostMapping
    @SecurityRequirement(name = "token")
    @Operation(summary = "Post Skill", description = "Post a Skill", responses = {
        @ApiResponse(responseCode = "200", description = "Successfully Posted Skill!", content = @Content(mediaType = "application/json", 
        schema = @Schema(implementation = SkillResponseDTO.class))),
        @ApiResponse(responseCode = "400", ref = "BadRequest"),
        @ApiResponse(responseCode = "401", ref = "badcredentials"),
        @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
        @ApiResponse(responseCode = "500", ref = "internalServerError")
})
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
    @Operation(summary = "Update Skill", description = "Update a Skill", responses = {
        @ApiResponse(responseCode = "200", description = "Successfully Update Skill!", content = @Content(mediaType = "application/json", 
        schema = @Schema(implementation = SkillResponseDTO.class))),
        @ApiResponse(responseCode = "400", ref = "BadRequest"),
        @ApiResponse(responseCode = "401", ref = "badcredentials"),
        @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
        @ApiResponse(responseCode = "500", ref = "internalServerError")
})
    @SecurityRequirement(name = "token")
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
    @SecurityRequirement(name = "token")
    @Operation(summary = "Delete Skill", description = "Delete a Skill", responses = {
        @ApiResponse(responseCode = "200", description = "Successfully Deleted Skill!"
      ),
        @ApiResponse(responseCode = "400", ref = "BadRequest"),
        @ApiResponse(responseCode = "401", ref = "badcredentials"),
        @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
        @ApiResponse(responseCode = "500", ref = "internalServerError")
})
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
