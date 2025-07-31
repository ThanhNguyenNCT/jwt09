package com.Cybersoft.uniclub09.services.imp;


import com.Cybersoft.uniclub09.entity.User;
import com.Cybersoft.uniclub09.repository.UserRepository;
import com.Cybersoft.uniclub09.services.AuthenticationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServicesImp implements AuthenticationServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean checkLogin(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        boolean isSuccessful = false;
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(passwordEncoder.matches(password, user.getPassword())) {
                isSuccessful = true;
            }
        }
        return isSuccessful;
    }


}
