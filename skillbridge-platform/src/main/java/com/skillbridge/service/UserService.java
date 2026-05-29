package com.skillbridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillbridge.entity.User;
import com.skillbridge.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        user.setRole("USER");

        return userRepository.save(user);
    }

    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid Password");
        }

        return user;
    }
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(Long id, User updatedUser) {

        User user = getUserById(id);

        user.setName(updatedUser.getName());
        user.setBio(updatedUser.getBio());
        user.setSkills(updatedUser.getSkills());
        user.setProfilePhoto(updatedUser.getProfilePhoto());
        
        user.setCollege(updatedUser.getCollege());
        user.setGithubUrl(updatedUser.getGithubUrl());
        user.setLinkedinUrl(updatedUser.getLinkedinUrl());
        user.setResumeUrl(updatedUser.getResumeUrl());
        user.setCompanyName(updatedUser.getCompanyName());

        return userRepository.save(user);
    }
    public int calculateProfileCompletion(User user) {

        int score = 0;

        if (user.getName() != null && !user.getName().isEmpty())
            score += 15;

        if (user.getBio() != null && !user.getBio().isEmpty())
            score += 15;

        if (user.getSkills() != null && !user.getSkills().isEmpty())
            score += 20;

        if (user.getProfilePhoto() != null && !user.getProfilePhoto().isEmpty())
            score += 15;

        if (user.getCollege() != null && !user.getCollege().isEmpty())
            score += 10;

        if (user.getGithubUrl() != null && !user.getGithubUrl().isEmpty())
            score += 10;

        if (user.getLinkedinUrl() != null && !user.getLinkedinUrl().isEmpty())
            score += 10;

        if (user.getResumeUrl() != null && !user.getResumeUrl().isEmpty())
            score += 5;

        return score;
    }
}