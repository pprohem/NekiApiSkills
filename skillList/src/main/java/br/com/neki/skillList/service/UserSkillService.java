package br.com.neki.skillList.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.skillList.dto.UserSkillDTO.UserSkillInsertDTO;
import br.com.neki.skillList.dto.UserSkillDTO.UserSkillReponseDTO;
import br.com.neki.skillList.exception.SkillException;
import br.com.neki.skillList.exception.UserException;
import br.com.neki.skillList.model.Skill;
import br.com.neki.skillList.model.User;
import br.com.neki.skillList.model.UserSkill;
import br.com.neki.skillList.repository.SkillRepository;
import br.com.neki.skillList.repository.UserRepository;
import br.com.neki.skillList.repository.UserSkillRepository;

@Service
public class UserSkillService {
    @Autowired
    private UserSkillRepository userSkillRepository;
  
    // Injecting the UserRepository into the UserSkillService class.
    @Autowired
    private SkillRepository skillRepository;
  
    // Injecting the UserRepository into the UserSkillService class.
    @Autowired
    private UserRepository userRepository;



    public List<UserSkillReponseDTO> findAll() {
        try {
          return userSkillRepository.findAll()
              .stream()
              .map(UserSkillReponseDTO::new)
              .collect(Collectors.toList());
        } catch (Exception e) {
          throw new RuntimeException("An error occurred while fetching user skills", e);
        }
      }


      public UserSkillReponseDTO findById(Long id) {
        return userSkillRepository.findById(id)
            .map(UserSkillReponseDTO::new)
            .orElseThrow(() -> new UserException("User Skill not found: id = " + id));
      }


      public UserSkill insert(UserSkillInsertDTO u) {

        if (u.getSkill() == null || u.getUser() == null) {
          throw new IllegalArgumentException("Invalid fields");
        }
    
        User user = userRepository.findById(u.getUser().getId())
            .orElseThrow(() -> new UserException("User not found: id = " + u.getUser().getId()));
    
        Skill skill = skillRepository.findById(u.getSkill().getId())
            .orElseThrow(() -> new SkillException("Skill not found: id = " + u.getSkill().getId()));
    
        // I'm trying to insert a skill into a user, but I can't insert the same skill
        // twice
        if (user.getUserSkill().stream().anyMatch(us -> us.getSkill().getId() == skill.getId())) {
          throw new SkillException("Impossible to insert the same skill");
        }
    
        UserSkill userSkill = new UserSkill();
        userSkill.setKnowledgeLevel(u.getKnowledgeLevel());
        userSkill.setCreatedAt(LocalDate.now());
        userSkill.setUpdatedAt(null);
        userSkill.setUser(user);
        userSkill.setSkill(skill);
        userSkill = userSkillRepository.save(userSkill);
    
        return userSkill;
    
      }



      public UserSkill update(Long id, UserSkillInsertDTO u) {

        if (u.getSkill() == null || u.getUser() == null) {
          throw new IllegalArgumentException("Invalid fields");
        }
        UserSkill userSkill = userSkillRepository.findById(id)
            .orElseThrow(() -> new UserException("User Skill not found: id = " + id));
    
        User user = userRepository.findById(u.getUser().getId())
            .orElseThrow(() -> new UserException("User not found: id = " + u.getUser().getId()));
    
        Skill skill = skillRepository.findById(u.getSkill().getId())
            .orElseThrow(() -> new SkillException("Skill not found: id = " + u.getSkill().getId()));
    
        // I'm trying to update a user skill, but I'm not allowed to change the user or
        // the skill
        if (!user.getId().equals(userSkill.getUser().getId())) {
          throw new SkillException("User cannot be changed");
        }
        if (!skill.getId().equals(userSkill.getSkill().getId())) {
          throw new SkillException("Skill cannot be changed");
        }
        userSkill.setKnowledgeLevel(u.getKnowledgeLevel());
        userSkill.setUpdatedAt(LocalDate.now());
        return userSkillRepository.save(userSkill);
    
      }


      public void delete(Long id) {

        userSkillRepository.findById(id)
            .orElseThrow(() -> new UserException("User Skill not found: id = " + id));
    
        userSkillRepository.deleteById(id);
    
      }
}
