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
        User user = userService.verifyUser(email, password); // OTP generated here

        if (user != null) {
            model.addAttribute("email", email);
            return "verifyOtp";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

    // Verify OTP
    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp,
                            Model model) {
        User user = userService.findByEmail(email);

        if (user != null && otp.equals(user.getOtp())) {
            user.setStatus("VERIFIED");
            userService.updateUser(user);
            return "home";
        } else {
            model.addAttribute("email", email);
            model.addAttribute("error", "Invalid OTP. Please try again or click Resend.");
            return "verifyOtp";
        }
    }

    // Resend OTP
    @PostMapping("/resendOtp")
    public String resendOtp(@RequestParam String email, Model model) {
        User user = userService.findByEmail(email);
        if (user != null) {
            String otp = otpService.generateOtp();
            user.setOtp(otp);
            userService.updateUser(user);
            otpService.sendOtp(email, otp);
        }
        model.addAttribute("email", email);
        return "verifyOtp";
    }
}