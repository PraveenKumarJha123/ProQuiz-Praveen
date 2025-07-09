package com.ProQuiz.service;

import com.ProQuiz.Dao.UserRepository;

import com.ProQuiz.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Users verifyUser(String email, String password) {
        // verify  user
        return userRepository.findByEmailAndPassword(email, password)
                .orElse(null);
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateUser(Users user) {
        userRepository.save(user);
    }
}