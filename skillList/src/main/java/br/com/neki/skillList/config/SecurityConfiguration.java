package br.com.neki.skillList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.neki.skillList.service.CustomUserDetailsService;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  // A dependency injection.
  private final CustomUserDetailsService usuarioService;


  /**
   * This function returns a new instance of the BCryptPasswordEncoder class.
   * 
   * @return A new instance of BCryptPasswordEncoder.
   */
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // A constructor.
  public SecurityConfiguration(CustomUserDetailsService usuarioService) {
    this.usuarioService = usuarioService;
  }

  /**
   * I'm going to use the AuthenticationManagerBuilder that was used to configure the HttpSecurity object
   * to build an AuthenticationManager that I can use to authenticate users.
   * 
   * @param http The HttpSecurity object that is used to configure the security of the application.
   * @return The AuthenticationManagerBuilder is being returned.
   */
  @Bean
  public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
      AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
      authenticationManagerBuilder.userDetailsService(usuarioService).passwordEncoder(bCryptPasswordEncoder());
      return authenticationManagerBuilder.build();
  }


  /**
   * This function is used to configure the security filter chain for the application.
   * 
   * @param http The HttpSecurity object that is used to configure the security filter chain.
   * @return A SecurityFilterChain
   */
  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().authorizeHttpRequests()
        .requestMatchers(HttpMethod.POST, "/login").permitAll()
        .requestMatchers(HttpMethod.GET, "/skill").permitAll()
        .requestMatchers(HttpMethod.GET, "/users/{id}").permitAll()
        .requestMatchers(HttpMethod.GET, "/users").permitAll()
        .requestMatchers(HttpMethod.GET, "/userskill").permitAll()
        .requestMatchers(HttpMethod.POST, "/users").permitAll()
          .requestMatchers("/v3/api-docs").permitAll()
            .requestMatchers("/configuration/ui").permitAll()
            .requestMatchers("/swagger-resources/**").permitAll()
            .requestMatchers("/configuration/security").permitAll()
            .requestMatchers("/swagger-ui.html").permitAll()
            .requestMatchers("/swagger-ui/*").permitAll()
            .requestMatchers("/webjars/**").permitAll()
            .requestMatchers("/v3/**").permitAll()
     

        .anyRequest().authenticated()
        .and()
        
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.headers().frameOptions().disable();

    http.apply(MyCustomDsl.customDsl());

    
    return http.build();
  }
 
}
