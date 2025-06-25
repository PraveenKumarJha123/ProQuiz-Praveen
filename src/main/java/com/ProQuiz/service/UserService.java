package com.ProQuiz.service;

import com.ProQuiz.Dao.UserRepository;
import com.ProQuiz.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User verifyUser(String email, String password) {
        // verify  user
        return userRepository.findByEmailAndPassword(email, password)
                .orElse(null);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}