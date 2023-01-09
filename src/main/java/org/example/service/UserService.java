package org.example.service;

import org.example.domain.Role;
import org.example.domain.User;
import org.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StmpMailSender stmpMailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if(userFromDb != null){
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);

        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Tweetie. Please visit next link: http://localhost:8080/activate/%s",
                            user.getUsername(), user.getActivationCode()
            );
            stmpMailSender.send(user.getEmail(), "Activation Code", message);
        }
        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if(user == null){
            return false;
        }

        user.setActivationCode(null);

        userRepository.save(user);
        return true;
    }
}
