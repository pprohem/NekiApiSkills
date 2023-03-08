package br.com.neki.skillList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class LoginResponseDTO {

  private Long id;
  private String username;
  private String token;

  public LoginResponseDTO(String username, Long id, String token) {
    this.id = id;
    this.username = username;
    this.token = token;
  }
}
