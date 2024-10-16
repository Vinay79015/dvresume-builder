package org.acm.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acm.entity.User;
import org.acm.entity.respository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    private final Map<String, String> users = new HashMap<>();

    @Transactional
    public boolean signUp(User user){
        if(userRepository.find("userName = ?1",user.getUserName()).firstResultOptional().isPresent()){
            return false;
        }
        userRepository.persist(user);
        return true;

    }

    public boolean login(String username, String password){
        return userRepository.find("userName = ?1 and passWord = ?2", username, password).firstResultOptional().isPresent();
    }

}
