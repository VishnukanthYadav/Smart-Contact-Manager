package com.scm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.helper.AppConstants;
import com.scm.helper.ResourceNotFoundException;
import com.scm.entities.User;
import com.scm.repositories.UserRepo;
import com.scm.services.UserService;

@Service
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserRepo useRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger=LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId=UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppConstants.ROLE_USER));
       return useRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
       return useRepo.findById(id);
    }

    @Override
    public Optional<User> updatUser(User user) {
       User user2=useRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("User Not Found"));
       user.setName(user.getName());
       user.setEmail(user.getEmail());
       user.setPassword(user.getPassword());
       user.setAbout(user.getAbout());
       user.setPhoneNumber(user.getPhoneNumber());
       user.setProfilePic(user.getProfilePic());
       user.setEnabled(user.isEnabled());
       user.setEmailVerified(user.isEmailVerified());
       user.setPhoneVerified(user.isPhoneVerified());
       user.setProvider(user.getProvider());
       user.setProviderUserId(user.getProviderUserId());
       User save= useRepo.save(user2);
       return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id) {
        User user2=useRepo.findById(id).
        orElseThrow(()->new ResourceNotFoundException("User Not Found"));
        useRepo.delete(user2);
    }

    @Override
    public boolean isUserExists(String userId) {
        User user2=useRepo.findById(userId).orElse(null);
        return user2!=null ? true : false;
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
       User user=useRepo.findByEmail(email).orElse(null);
       return user!=null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
      return useRepo.findAll();
    }

}
