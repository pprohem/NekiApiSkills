package br.com.neki.skillList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.skillList.model.UserSkill;

public interface UserSkillRepository extends JpaRepository <UserSkill, Long> {
    
}
