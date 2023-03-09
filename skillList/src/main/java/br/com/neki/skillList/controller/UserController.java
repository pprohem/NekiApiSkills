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

import br.com.neki.skillList.dto.SkillDTO.UserDTO.UserResponseDTO;
import br.com.neki.skillList.dto.UserSkillDTO.UserInsertDTO;
import br.com.neki.skillList.exception.UserException;
import br.com.neki.skillList.model.User;
import br.com.neki.skillList.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
@Tag(name = "User Actions", description = "Actions for a User")
public class UserController {
    

  @Autowired
  private UserService userService;



 @GetMapping("{id}")
 @Operation(summary = "Get a Skill", description = "Get one Skill", responses = {
  @ApiResponse(responseCode = "200", description = "Successfully Get Skill"
 ),
  @ApiResponse(responseCode = "400", ref = "BadRequest"),
  @ApiResponse(responseCode = "401", ref = "badcredentials"),
  @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
  @ApiResponse(responseCode = "500", ref = "internalServerError")
})
  public ResponseEntity<Object> findById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(userService.findById(id));
    } catch (UserException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
  }

 @GetMapping
 @Operation(summary = "Get All Users", description = "Get All Users", responses = {
  @ApiResponse(responseCode = "200", description = "Successfully Get all Users!"
 ),
  @ApiResponse(responseCode = "400", ref = "BadRequest"),
  @ApiResponse(responseCode = "401", ref = "badcredentials"),
  @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
  @ApiResponse(responseCode = "500", ref = "internalServerError")
})
  public ResponseEntity<Object> findAll() {
    try {
      return ResponseEntity.ok(userService.findAll());
    } catch (RuntimeException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
  }

  @PostMapping
  @Operation(summary = "Post User", description = "Post a User", responses = {
    @ApiResponse(responseCode = "200", description = "Successfully Posted User!", content = @Content(mediaType = "application/json", 
    schema = @Schema(implementation = UserResponseDTO.class))),
    @ApiResponse(responseCode = "400", ref = "BadRequest"),
    @ApiResponse(responseCode = "401", ref = "badcredentials"),
    @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
    @ApiResponse(responseCode = "500", ref = "internalServerError")
})
  public ResponseEntity<Object> insert(@Valid @RequestBody UserInsertDTO user) {
    try {
      User userInsert = userService.insert(user);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(userInsert.getId())
          .toUri();
      return ResponseEntity.created(uri).body(userInsert);
    } catch (UserException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
  }

  @PutMapping("{id}")
  @Operation(summary = "Update User", description = "Update a User", responses = {
    @ApiResponse(responseCode = "200", description = "Successfully Update User!", content = @Content(mediaType = "application/json", 
    schema = @Schema(implementation = UserResponseDTO.class))),
    @ApiResponse(responseCode = "400", ref = "BadRequest"),
    @ApiResponse(responseCode = "401", ref = "badcredentials"),
    @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
    @ApiResponse(responseCode = "500", ref = "internalServerError")
})
  @SecurityRequirement(name = "token")
  public ResponseEntity<Object> insert(@Valid @PathVariable Long id, @RequestBody UserInsertDTO user) {
    try {
      User userInsert = userService.update(id, user);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
          .buildAndExpand(userInsert.getId())
          .toUri();
      return ResponseEntity.created(uri).body(userInsert);
    } catch (UserException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
  }

  @DeleteMapping("{id}")
  @Operation(summary = "Delete User", description = "Delete a User", responses = {
    @ApiResponse(responseCode = "200", description = "Successfully Deleted User!"
  ),
    @ApiResponse(responseCode = "400", ref = "BadRequest"),
    @ApiResponse(responseCode = "401", ref = "badcredentials"),
    @ApiResponse(responseCode = "422", ref = "unprocessableEntity"),
    @ApiResponse(responseCode = "500", ref = "internalServerError")
})
  @SecurityRequirement(name = "token")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    try {
      userService.delete(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (UserException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

  }
}
