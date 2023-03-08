package br.com.neki.skillList.service;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.neki.skillList.data.CustomUserDetails;
import br.com.neki.skillList.model.User;
import br.com.neki.skillList.repository.UserRepository;



@Component
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("Username " + username + "não encontrado");
    }

    //NOTE: Update last login date
    User usr = user.get();
    usr.setLastLoginDate(LocalDate.now());
    userRepository.save(usr);


    return new CustomUserDetails(user);
  }

}
