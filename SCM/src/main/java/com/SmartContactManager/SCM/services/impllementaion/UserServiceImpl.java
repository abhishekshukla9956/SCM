package com.SmartContactManager.SCM.services.impllementaion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.SmartContactManager.SCM.helpers.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SmartContactManager.SCM.Entities.User;
import com.SmartContactManager.SCM.repository.UserRepo;
import com.SmartContactManager.SCM.services.UserService;

import com.SmartContactManager.SCM.helpers.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Data
@Setter
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use: " + user.getEmail());
        }
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoleList(List.of(AppConstants.Role_User));

        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User existingUser = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + user.getUserId()));

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setPassword(user.getPassword());
        existingUser.setEnabled(user.isEnabled());
        existingUser.setEmailVerified(user.isEmailVerified());
        existingUser.setPhoneVerified(user.isPhoneVerified());
        existingUser.setProvider(user.getProvider());
        existingUser.setProviderUserId(user.getProviderUserId());

        User savedUser = userRepo.save(existingUser);
        return Optional.ofNullable(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
         userRepo.delete(user2);
    }

    @Override
    public boolean isUserExists(String UserId) {
        User user2 =userRepo.findByEmail(UserId).orElse(null);
        return user2 != null ? true : false;
    }
 
    @Override
    public boolean findByEmail(String email) {
        User user2 = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
       return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


}
