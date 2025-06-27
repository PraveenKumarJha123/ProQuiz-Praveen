package com.ProQuiz.controller;

import com.ProQuiz.Entity.User;
import com.ProQuiz.service.OtpService;
import com.ProQuiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/verifyLogin")
    public String verifyLogin(@RequestParam String email,
                              @RequestParam String password,
                              Model model) {

        User user = userService.verifyUser(email, password);
        if (user != null) {
            // Only send OTP if otp is not already sent
            otpService.sendOtp(email);
            model.addAttribute("email", email);
            return "verifyOtp";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp,
                            Model model) {

        boolean isVerified = otpService.verifyOtp(email, otp);
        if (isVerified) {
            User user = userService.findByEmail(email);
            user.setStatus("VERIFIED");
                 userService.updateUser(user);

            return "home";
        } else {
            model.addAttribute("email", email);

            model.addAttribute("error", "Invalid OTP. Please try again or click Resend.");
            return "verifyOtp";
        }
    }

    @PostMapping("/resendOtp")
    public String resendOtp(@RequestParam String email, Model model) {
        // this generates always   a new OTP when i click resending
        User user = userService.findByEmail(email);
        if (user != null) {
           user.setOtp(null); // Clear axisting OTP first
            userService.updateUser(user);
            otpService.sendOtp(email); // it Will now generate new OTP
            model.addAttribute("otpSend" , "Resend request sent successfully please see your email");

        }
        model.addAttribute("email", email);
        model.addAttribute("error","this new Otp will valid only within 2 min");
        return "verifyOtp";
    }

    @GetMapping("/forgotPassword")
    public String forgotPasswordPage() {
        return "forgotPassword";
    }

    @PostMapping("/sendResetOtp")
    public String sendResetOtp(@RequestParam String email, Model model) {
        User user = userService.findByEmail(email);
        if (user != null) {
            otpService.sendOtp(email); // Generate and send OTP only once
            model.addAttribute("email", email);
            model.addAttribute("otpSend","otp send your registered email , please check");
            return "forgotPassword";
        } else {
            model.addAttribute("error", "Email not found.");
            return "forgotPassword";
        }
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String otp,
                                @RequestParam String newPassword,
                                Model model) {

        boolean isVerified = otpService.verifyOtp(email, otp);
        if (isVerified) {
            User user = userService.findByEmail(email);
            user.setPassword(newPassword);
            userService.updateUser(user);
            return "login";
        } else {
            model.addAttribute("error", "Invalid OTP.");
            model.addAttribute("email", email);
            return "forgotPassword";
        }
    }
}