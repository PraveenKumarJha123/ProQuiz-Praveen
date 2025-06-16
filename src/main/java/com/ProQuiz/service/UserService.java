package com.ProQuiz.service;

import com.ProQuiz.Dao.UserRepository;
import com.ProQuiz.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {


        @Autowired
        private UserRepository userRepository;

        @Autowired
        private OtpService otpService;

        public User verifyUser(String email, String password) {
            User user = userRepository.findByEmailAndPassword(email, password)
                    .orElse(null);

            if (user != null) {
                String otp = otpService.generateOtp();
                user.setOtp(otp);
                userRepository.save(user); // save OTP in DB
                otpService.sendOtp(user.getEmail(), otp); // send OTP via email
            }

            return user;
        }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

}