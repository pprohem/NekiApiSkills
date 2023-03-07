package br.com.neki.skillList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.skillList.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Boolean existsByNameIgnoreCase(String name);
}
