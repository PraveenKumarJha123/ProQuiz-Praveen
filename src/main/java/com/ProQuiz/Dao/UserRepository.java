package com.ProQuiz.Dao;


import com.ProQuiz.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailAndPassword(String email, String password);

    Users findByEmail(String email);
    // Custom to verify OTP
    Optional<Users> findByEmailAndOtp(String email, String otp);
}