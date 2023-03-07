package br.com.neki.skillList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neki.skillList.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
