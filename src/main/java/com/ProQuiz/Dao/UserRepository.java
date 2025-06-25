package com.ProQuiz.Dao;

import com.ProQuiz.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
    // Custom to verify OTP
    Optional<User> findByEmailAndOtp(String email, String otp);
}