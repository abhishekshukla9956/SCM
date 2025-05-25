package com.SmartContactManager.SCM.services;

import java.util.List;
import java.util.Optional;

import com.SmartContactManager.SCM.Entities.User;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUserById(String id);

    Optional<User> updateUser(User user);

    void deleteUser(String id);

    boolean isUserExists(String email);

    boolean findByEmail(String email);

    List <User> getAllUsers();
}
