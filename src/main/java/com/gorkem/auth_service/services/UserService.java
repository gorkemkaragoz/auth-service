package com.gorkem.auth_service.services;

import com.gorkem.auth_service.entities.User;

import java.util.List;

public interface UserService {

    public User getOneUser(Long userId);
    public List<User> getAllUsers();
    public User createUser(User newUser);
    public User updateUser(Long userId, User updateUser);
    void deleteUser(Long userId);

}
