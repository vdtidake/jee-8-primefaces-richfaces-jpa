package edu.vdt.service;

import edu.vdt.entity.User;
import edu.vdt.exception.UserNotFoundException;
import edu.vdt.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public UUID createUser(User user){
        return userRepository.createUser(user);
    }

    public boolean isValidUser(User user){
        try{
            return userRepository.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()) != null;
        }catch (UserNotFoundException une){
            return false;
        }
    }
}
