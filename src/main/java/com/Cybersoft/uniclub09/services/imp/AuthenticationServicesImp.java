package com.Cybersoft.uniclub09.services.imp;


import com.Cybersoft.uniclub09.entity.Role;
import com.Cybersoft.uniclub09.entity.User;
import com.Cybersoft.uniclub09.exception.CentralException;
import com.Cybersoft.uniclub09.exception.DataNotFoundExceptions;
import com.Cybersoft.uniclub09.exception.MethodNotAllowExceptions;
import com.Cybersoft.uniclub09.repository.UserRepository;
import com.Cybersoft.uniclub09.services.AuthenticationServices;
import com.Cybersoft.uniclub09.util.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServicesImp implements AuthenticationServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private CentralException centralException;

    @Override
    public String checkLogin(String email, String password) {
//        Optional<User> userOptional = userRepository.findByEmail(email);
//
//        if(userOptional.isPresent()) {
//            User user = userOptional.get();
//            if(passwordEncoder.matches(password, user.getPassword())) {
//                String roleName = user.getRole().getName();
//                return jwtHelper.generateToken(roleName);
//            }else {
//                throw new RuntimeException("Invalid password for user with email: " + email);
//            }
//        } else{
//            throw new RuntimeException("User not found with email: " + email);
//        }
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundExceptions("User not found with email: " + email));
        if(passwordEncoder.matches(password, user.getPassword())) {
                String roleName = user.getRole().getName();
                return jwtHelper.generateToken(roleName);
            }else {
                throw new MethodNotAllowExceptions("Invalid password for user with email: " + email);
            }
    }

    @Override
    public void signup(String email, String password, String fullName) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        Role role = new Role();
        role.setId(2); // Assuming "2" is the ID for the default role

        user.setRole(role);

        userRepository.save(user);
    }


}
