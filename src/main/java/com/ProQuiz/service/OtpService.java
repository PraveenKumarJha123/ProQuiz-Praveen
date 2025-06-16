package com.ProQuiz.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;


    public String generateOtp() {
        return String.format("%06d", new Random().nextInt(1000000)); // Pads to 6 digits
    }

    public void sendOtp(String email, String otp) {
        System.out.println("Sending OTP " + otp + " to email: " + email);
        // You can add real email sending logic here
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);  // e.g., test123@mailinator.com
        message.setSubject("Your OTP for ProQuiz");
        message.setText("Dear User,\n\nYour OTP is: " + otp + "\n\nThanks,\nProQuiz Team");
        mailSender.send(message);
    }
}