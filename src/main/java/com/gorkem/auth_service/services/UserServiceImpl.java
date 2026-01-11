package com.gorkem.auth_service.services;

import com.gorkem.auth_service.entities.Role;
import com.gorkem.auth_service.entities.User;
import com.gorkem.auth_service.repos.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User newUser) {
        if(newUser.getRole() == null){
            newUser.setRole(Role.ROLE_MEMBER);
        }
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(Long userId, User updateUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            User foundUser = user.get();
            foundUser.setFirstName(updateUser.getFirstName());
            foundUser.setLastName(updateUser.getLastName());
            foundUser.setEmail(updateUser.getEmail());
            foundUser.setPassword(updateUser.getPassword());
            return userRepository.save(foundUser);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
