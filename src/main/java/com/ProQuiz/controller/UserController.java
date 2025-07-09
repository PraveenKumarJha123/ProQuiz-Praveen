package com.ProQuiz.controller;

import com.ProQuiz.Entity.Users;
import com.ProQuiz.service.OtpService;
import com.ProQuiz.service.UserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "This is user Controller for showLoginPage", response = String.class , notes = "Userservice which contains only page rendering ")
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @ApiOperation(value = "This is user Controller for verifyLogin", response = String.class , notes = "Userservice which is used for verifyLogin login page ")

    @PostMapping("/verifyLogin")
    public String verifyLogin(@RequestParam String email,
                              @RequestParam String password,

                              Model model) {

        Users user = userService.verifyUser(email, password);
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
    @ApiOperation(value = "This is user Controller for verifyOtp", response = String.class , notes = "User service which is used  verifyOtp for  login page ")


    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp,
                            Model model) {

        boolean isVerified = otpService.verifyOtp(email, otp);

        if (isVerified) {
            Users user = userService.findByEmail(email);

            if (user != null) {
                user.setStatus("VERIFIED");
                userService.updateUser(user);

                String user_role = user.getUser_role();  // Assuming 'role' is a field in your Users entity

                if ("teacher".equalsIgnoreCase(user_role)) {
                    return "teacher";  // Redirects to teacher.jsp

                } else if("Student".equalsIgnoreCase(user_role)) {
                    return "Student";     // For Student roles
                } else {
                    return "Admin";
                }

            } else {
                model.addAttribute("error", "User not found.");
                return "verifyOtp";
            }
        } else {
            model.addAttribute("email", email);
            model.addAttribute("error", "Invalid OTP. Please try again or click Resend.");
            return "verifyOtp";
        }
    }
    @ApiOperation(value = "This is user Controller for resendOtp", response = String.class , notes = "User service which is used  for  resendOtp ")


    @PostMapping("/resendOtp")
    public String resendOtp(@RequestParam String email, Model model) {
        // this generates always   a new OTP when i click resending
        Users user = userService.findByEmail(email);
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
    @ApiOperation(value = "This is user Controller for forgotPassword", response = String.class , notes = "User service which is used   for gotPassword ")

    @GetMapping("/forgotPassword")
    public String forgotPasswordPage() {
        return "forgotPassword";
    }
    @ApiOperation(value = "This is user Controller for sendResetOtp", response = String.class , notes = "User service which is used   for sendResetOtp ")

    @PostMapping("/sendResetOtp")
    public String sendResetOtp(@RequestParam String email, Model model) {
        Users user = userService.findByEmail(email);
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
    @ApiOperation(value = "This is user Controller for resetPassword", response = String.class , notes = "User service which is used for resetPassword ")

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String email,
                                @RequestParam String otp,
                                @RequestParam String newPassword,
                                Model model) {

        boolean isVerified = otpService.verifyOtp(email, otp);
        if (isVerified) {
            Users user = userService.findByEmail(email);
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