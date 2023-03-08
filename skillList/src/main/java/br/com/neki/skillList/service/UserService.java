package br.com.neki.skillList.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.neki.skillList.dto.SkillDTO.UserDTO.UserResponseDTO;
import br.com.neki.skillList.dto.UserSkillDTO.UserInsertDTO;
import br.com.neki.skillList.exception.UserException;
import br.com.neki.skillList.model.User;
import br.com.neki.skillList.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository  userRepository; 

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    



    public List<UserResponseDTO> findAll() {
        try {
          return userRepository.findAll().stream()
              .map(UserResponseDTO::new)
              .collect(Collectors.toList());
        } catch (Exception e) {
          throw new RuntimeException("An error occurred while fetching user skills", e);
        }
      }


      public UserResponseDTO findById(Long id) {
        return userRepository.findById(id)
            .map(UserResponseDTO::new)
            .orElseThrow(() -> new UserException("User not found: id = " + id));
      }

      public User insert(UserInsertDTO u) {

        if (userRepository.existsByUsername(u.getUsername())) {
          throw new UserException("Username already registered");
        }
    
        User user = new User();
        user.setUsername(u.getUsername());
        // Encrypting the password.
        user.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        user.setLastLoginDate(null);
        user = userRepository.save(user);
    
        return user;
    
      }

      public User update(Long id, UserInsertDTO u) {

        // A way to check if the user exists, if it does not exist, it throws an
        // exception.
        User userC = userRepository.findById(id).orElseThrow(() -> new UserException("User not found: id = " + id));
    
        // Checking if the username is already registered.
        if (!userC.getUsername().equals(u.getUsername()) && userRepository.existsByUsername(u.getUsername())) {
          throw new UserException("Username already registered");
        }
    
        User user = new User();
        user.setId(id);
        user.setUsername(u.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        user.setLastLoginDate(userC.getLastLoginDate());
        user = userRepository.save(user);
    
        return user;
    
      }

      public void delete(Long id) {

        userRepository.findById(id)
            .orElseThrow(() -> new UserException("User not found: id= " + id));
    
        userRepository.deleteById(id);
    
      }
}
