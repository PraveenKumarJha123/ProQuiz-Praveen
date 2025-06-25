package com.ProQuiz.service;

import com.ProQuiz.Dao.UserRepository;
import com.ProQuiz.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
@Service
public class OtpService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;
public boolean verifyOtp(String email, String enteredOtp) {
    Optional<User> userOpt = userRepository.findByEmailAndOtp(email, enteredOtp);
    if (userOpt.isPresent()) {
        User user = userOpt.get();
       // user.setOtp(null); // Clear OTP after verification
        userRepository.save(user);
        return true;
    }
    return false;
}


    // Generate or return existing OTP

        public String generateOtp(String email) {

            User user = userRepository.findByEmail(email);

            if (user != null && user.getOtp() != null && !user.getOtp().isEmpty()) {
                return user.getOtp(); // Return existing OTP
            }
              else{
                user.setOtp(null);
                String otp = String.format("%06d", new Random().nextInt(1000000));
                user.setOtp(otp);  // Store OTP in DB
                userRepository.save(user);
            }

            return user.getOtp();
        }
    // Send OTP to email

        public void sendOtp(String email) {
            String otp = generateOtp(email);
            System.out.println("Sending OTP " + otp + " to email: " + email);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Your OTP for ProQuiz");
            message.setText("Dear User,\n\nYour OTP is: " + otp + "\n\nThanks,\nProQuiz Team");
            mailSender.send(message);
        }

}

