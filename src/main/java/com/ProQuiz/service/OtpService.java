package com.ProQuiz.service;

import com.ProQuiz.Dao.UserRepository;
import com.ProQuiz.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
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

        if (user.getOtpGeneratedTime() != null) {
            long diff = new Date().getTime() - user.getOtpGeneratedTime().getTime();
            if (diff <= 2 * 60 * 1000) { // 2 minutes
                // check Valid OTP
                user.setOtp(null); // if Otp verified then Clear OTP after successful verification
                user.setOtpGeneratedTime(null);
                userRepository.save(user);
                return true;
            }
        }
    }

    return false; //means it gives  OTP not found or expired
}


    // Generate or return existing OTP
public String generateOtp(String email) {
    User user = userRepository.findByEmail(email);

    // Check if OTP already exists and is not expired
    if (user.getOtp() != null && user.getOtpGeneratedTime() != null) {
        long diff = new Date().getTime() - user.getOtpGeneratedTime().getTime();
        if (diff < 2 * 60 * 1000) { // 2 minutes
            return user.getOtp(); // Return existing OTP
        }
    }

    // Generate new OTP
    String otp = String.format("%06d", new Random().nextInt(1000000));
    user.setOtp(otp);
    user.setOtpGeneratedTime(new Date());
    userRepository.save(user);
    return otp;
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

