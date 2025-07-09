package com.ProQuiz.Entity;



import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

 @Setter
 @Getter
 @NoArgsConstructor
 @AllArgsConstructor
@Entity
 @ApiModel(value = "This is User amd its contains user id, full name , email, phone, password, user role, otp , status and otpGeneratedTime ")
 @Table(name="myusers")
public class Users {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(value = "this is user ID and PK")
    private Long user_id;
     @ApiModelProperty(value = "this is user  full name")
    private String fullname;
     @ApiModelProperty(value = "this is user  email Id")

     private String email;
     @ApiModelProperty(value = "this is user  Phone")

     private Long phone;
     @ApiModelProperty(value = "this is user  password")

     private String password;
     @ApiModelProperty(value = "this is user  user_role")

     private String user_role;
     @ApiModelProperty(value = "this is user  otp")

     private String otp;
     @ApiModelProperty(value = "this is user  status")

     private String status;  // "INACTIVE" or "ACTIVE"
     @ApiModelProperty(value = "this is user  otpGeneratedTime")
     private Date otpGeneratedTime;

    //use lombok

}
