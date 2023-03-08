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

import br.com.neki.skillList.dto.UserSkillDTO.UserInsertDTO;
import br.com.neki.skillList.exception.UserException;
import br.com.neki.skillList.model.User;
import br.com.neki.skillList.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    

  @Autowired
  private UserService userService;



 @GetMapping("{id}")
  public ResponseEntity<Object> findById(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(userService.findById(id));
    } catch (UserException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
  }

 @GetMapping
  public ResponseEntity<Object> findAll() {
    try {
      return ResponseEntity.ok(userService.findAll());
    } catch (RuntimeException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }
  }

  @PostMapping
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
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    try {
      userService.delete(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    } catch (UserException e) {
      return ResponseEntity.unprocessableEntity().body(e.getMessage());
    }

  }
}
