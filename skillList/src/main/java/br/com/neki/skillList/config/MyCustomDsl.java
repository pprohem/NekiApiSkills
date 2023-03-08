package br.com.neki.skillList.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Service;

import br.com.neki.skillList.security.JWTAuthenticateFilter;
import br.com.neki.skillList.security.JWTValidationFilter;

/**
 * This class is a custom DSL that adds two filters to the Spring Security filter chain.
 */
@Service
public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
 /**
  * The configure function is called by the Spring Security framework to configure the security of the
  * application
  * 
  * @param http The HttpSecurity object that is used to configure the security of the application.
  */
  @Override
  public void configure(HttpSecurity http) throws Exception {
      AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
      http.addFilter(new JWTAuthenticateFilter(authenticationManager));
      http.addFilter(new JWTValidationFilter(authenticationManager));
  }

  /**
   * It returns a new instance of the MyCustomDsl class
   * 
   * @return A new instance of the MyCustomDsl class.
   */
  public static MyCustomDsl customDsl() {
      return new MyCustomDsl();
  }
}