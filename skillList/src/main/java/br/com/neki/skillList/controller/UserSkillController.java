package br.com.neki.skillList.controller;

import java.net.URI;

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

import br.com.neki.skillList.dto.UserSkillDTO.UserSkillInsertDTO;
import br.com.neki.skillList.dto.UserSkillDTO.UserSkillReponseDTO;
import br.com.neki.skillList.exception.SkillException;
import br.com.neki.skillList.exception.UserException;
import br.com.neki.skillList.model.UserSkill;
import br.com.neki.skillList.service.UserSkillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("userskill")
@Tag(name = "User Skills", description = "Skills of a specific user")
public class UserSkillController {
    
    @Autowired
    private UserSkillService userSkillService;


    @GetMapping
    @Operation(summary = "Get All Users", description = "Get All", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully Get all Users!"
     ),
      @ApiResponse(responseCode = "400", ref = "BadRequest"),
      @ApiResponse(responseCode = "401", ref = "badcredentials"),
      @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
})
    public ResponseEntity<Object> findAll() {
      try {
        return ResponseEntity.ok(userSkillService.findAll());
      } catch (RuntimeException e) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
      }
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a User by Id", description = "Get one User", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully Get User"
     ),
      @ApiResponse(responseCode = "400", ref = "BadRequest"),
      @ApiResponse(responseCode = "401", ref = "badcredentials"),
      @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
    })
    public ResponseEntity<Object> findById(@PathVariable Long id) {
      try {
        return ResponseEntity.ok(userSkillService.findById(id));
      } catch (UserException e) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
      }
    }

    @PostMapping
    @SecurityRequirement(name = "token")
    @Operation(summary = "Post UserSkill", description = "Post a UserSkill", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully Posted UserSkill!", content = @Content(mediaType = "application/json", 
      schema = @Schema(implementation = UserSkillReponseDTO.class))),
      @ApiResponse(responseCode = "400", ref = "BadRequest"),
      @ApiResponse(responseCode = "401", ref = "badcredentials"),
      @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
  })
    public ResponseEntity<Object> insert(@Valid @RequestBody UserSkillInsertDTO user) {
      try {
        UserSkill userInsert = userSkillService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(userInsert.getId())
            .toUri();
        return ResponseEntity.created(uri).body(userInsert);
      } catch (UserException | SkillException | IllegalArgumentException e) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
      }
  
    }
    @PutMapping
    @SecurityRequirement(name = "token")
    @Operation(summary = "Update UserSkill", description = "Update a UserSkill", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully Update UserSkill!", content = @Content(mediaType = "application/json", 
      schema = @Schema(implementation = UserSkillReponseDTO.class))),
      @ApiResponse(responseCode = "400", ref = "BadRequest"),
      @ApiResponse(responseCode = "401", ref = "badcredentials"),
      @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
  })
    public ResponseEntity<Object> insert(@Valid @PathVariable Long id, @RequestBody UserSkillInsertDTO user) {
  
      try {
        UserSkill userInsert = userSkillService.update(id, user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(userInsert.getId())
            .toUri();
        return ResponseEntity.created(uri).body(userInsert);
      } catch (UserException | SkillException | IllegalArgumentException e) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
      }
  
    }   
    @DeleteMapping("{id}")
    @Operation(summary = "Delete UserSkill", description = "Delete a User Skill", responses = {
      @ApiResponse(responseCode = "200", description = "Successfully UserSkill delete!"
    ),
      @ApiResponse(responseCode = "400", ref = "BadRequest"),
      @ApiResponse(responseCode = "401", ref = "badcredentials"),
      @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
      @ApiResponse(responseCode = "500", ref = "internalServerError")
})
    @SecurityRequirement(name = "token")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
      try {
        userSkillService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      } catch (UserException e) {
        return ResponseEntity.unprocessableEntity().body(e.getMessage());
      }
  
    }
}
