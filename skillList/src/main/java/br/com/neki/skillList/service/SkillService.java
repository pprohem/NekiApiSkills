package br.com.neki.skillList.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.skillList.dto.SkillDTO.SkillRequestDTO;
import br.com.neki.skillList.dto.SkillDTO.SkillResponseDTO;
import br.com.neki.skillList.exception.SkillException;
import br.com.neki.skillList.model.Skill;
import br.com.neki.skillList.repository.SkillRepository;
import jakarta.transaction.Transactional;

@Service
public class SkillService {
    
    @Autowired
    private SkillRepository skillRepository;

    public List<SkillResponseDTO> findAll() { 
        List<Skill> sk = skillRepository.findAll();
        List<SkillResponseDTO> res = new ArrayList<>();
        for (Skill skill : sk) {
            res.add(new SkillResponseDTO(skill));
        }
        return res;
    }
    



    @Transactional
    public SkillResponseDTO registerSkill(SkillRequestDTO skRequest) {
      if(skillRepository.existsByNameIgnoreCase(skRequest.getName())){
        throw new SkillException("The skill already exists"); 
      }

      Skill sk = new Skill();
      sk.setName(skRequest.getName());
      sk.setDescription(skRequest.getDescription());
      sk.setImageUrl(skRequest.getImageUrl());
      sk.setVersion(skRequest.getVersion());

      sk = skillRepository.save(sk);
      return new SkillResponseDTO(sk);
    }

    @Transactional
    public SkillResponseDTO updateSkill (Long id, SkillRequestDTO skRequest) {
        Skill sk = skillRepository.findById(id).orElseThrow(() -> new SkillException("Could not find category id"
        + id));

        String name = sk.getName();

        if(!sk.getName().equalsIgnoreCase(name) && skillRepository.existsByNameIgnoreCase(name)) { 
            throw new SkillException("Name already exists for category name = " + name);
        }

        sk.setName(skRequest.getName());
        sk.setDescription(skRequest.getDescription());
        sk.setImageUrl(skRequest.getImageUrl());
        sk.setVersion(skRequest.getVersion());

        sk = skillRepository.save(sk);
        return new SkillResponseDTO(sk); 
    }

    @Transactional
    public void deleteSkill(Long id){
        Skill sk = skillRepository.findById(id)
        .orElseThrow(( ) -> new SkillException("Could not find skill id = " + id));

        skillRepository.deleteById(id);
        
    }

    


}
