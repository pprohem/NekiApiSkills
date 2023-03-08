package br.com.neki.skillList.security;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.util.UrlPathHelper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.neki.skillList.data.CustomUserDetails;
import br.com.neki.skillList.dto.ErrorResponseDTO;
import br.com.neki.skillList.dto.LoginResponseDTO;
import br.com.neki.skillList.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {

  public static final Date expirationDate = new Date(System.currentTimeMillis() + 900000);

  public static final String TOKEN_PASSWORD = "b38be612-0681-4ab7-8478-1d04e8127f3d";

  private final AuthenticationManager authenticationManager;

  private final static UrlPathHelper urlPathHelper = new UrlPathHelper();

  public JWTAuthenticateFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    try {
      User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
      if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null
          || user.getPassword().isEmpty()) {
        throw new AuthenticationCredentialsNotFoundException("Username and password are required");
      }
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
    } catch (IOException e) {
      throw new AuthenticationServiceException("Failed to authenticate user", e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {

    CustomUserDetails usarioData = (CustomUserDetails) authResult.getPrincipal();

    String uniqueId = UUID.randomUUID().toString();

    String token = JWT.create()
        .withSubject(usarioData.getUsername())
        .withJWTId(uniqueId)
        .withExpiresAt(expirationDate)
        .sign(Algorithm.HMAC512(TOKEN_PASSWORD));

    response.addHeader("Authorization", "Bearer " + token);
    response.getWriter().write(new ObjectMapper()
        .writeValueAsString(new LoginResponseDTO(usarioData.getUsername(), usarioData.getId(), token)));
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {

    logger.debug("failed authentication while attempting to access "
        + urlPathHelper.getPathWithinApplication((HttpServletRequest) request));
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write(new ObjectMapper().writeValueAsString(new ErrorResponseDTO("Invalid credentials")));
    response.getWriter().flush();
  }

}
