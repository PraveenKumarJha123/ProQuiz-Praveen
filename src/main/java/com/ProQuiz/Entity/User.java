package com.ProQuiz.Entity;



import lombok.*;

import javax.persistence.*;
import java.util.Date;

 @Setter
 @Getter
 @NoArgsConstructor
 @AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;
    @Column(name="fullName")
    private String fullName;

    private String email;
    private String password;
    private String otp;
    private String status;  // "INACTIVE" or "ACTIVE"
     private Date otpGeneratedTime;

    //use lombok

}
