package br.com.neki.skillList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neki.skillList.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository  userRepository; 


    
}
