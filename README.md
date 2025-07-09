# Maven dependecy tree
mvn dependency:tree > pro.txt
<<<<<<< HEAD
All Updated





## Technologies Used

1. Java 17
2. Spring Boot 2.x
3. Spring MVC
4.  Hibernate (JPA)
5. JSP (View Layer)
6. MySQL (Database) 8.x
7. JavaMailSender (for OTP email)
8. Swagger 2 (for API Documentation and testing)
9. Maven(<packaging>war</packaging>)
10. 
##  Project Structure
 1. Base-Package -> com.proQuiz
       class-name- > ProQuizApplication
    sub-Package name -> controller (Contains UserController for login, OTP, password handling)
    sub-Package name -> service (Contains Business logic ( two class 1.UserService, 2. OtpService)
    sub-Package name -> dao (Contains UserRepository extends JpaRepository for Data Access Layer
    sub-Package name -> entity (Contains JPA Entities for Users)
    sub-Package name -> configSwagger (Contains  Swagger configuration

## Email Configuration (By using JavaMailSender)

spring:
    mail:
        host: smtp.gmail.com
        port: 587
        username: praveenjhaazad143@gmail.com
        password: bmtcaxdqeblnfotk
        properties:
            mail:
                smtp:
                    auth: true
                    starttls:
                        enable: true


# For DataBase (MySQL 8.x)
 for manually create Database  -> CREATE DATABASE proquiz;
        but i am using  this 
 1.        spring:
             jpa:
                 hibernate:
                     ddl-auto: update /create
 2. For show table
      SELECT * FROM quizapp.myusers;
 3. For Insert Querry
    insert into  quizapp.myusers(user_id,email,fullname,password,phone,user_role) values("<user_id>","<user email id>" ,"<User full name>","<user Password>",<User Phone number>, "<User_Role>");
 4. For Update Querry
    UPDATE  quizapp.myusers SET fullname = "<New Update full Name>" WHERE (user_id = <which user id you want>);
 5. For Delete Querry
    DELETE FROM quizapp.myusers WHERE user_id = <Which user id you want to delete>;


## API Endpoints (UserController)
    Method	    Endpoint	            Description
1    GET	    /login	                Show login page
2    POST	    /verifyLogin	        Validate login & send OTP
3    POST	    /verifyOtp	            Verify OTP and login
4    POST	    /resendOtp	            Resend new OTP
5    GET	    /forgotPassword	        Forgot password page
6    POST	    /sendResetOtp	        Send OTP to reset password
7    POST	    /resetPassword	        Reset password via OTP


## Swagger UI
 Url -> http://localhost:8080/swagger-ui.html

## All Api Endpoints Url                        Method                               Purpose 
1. http://localhost:9090/login                  GET                                   Show login page
2. http://localhost:9090/verifyLogin            POST                                  Validate login & send OTP
3. http://localhost:9090/verifyOtp              POST                                  Verify OTP and login
4. http://localhost:9090/resendOtp              POST                                  Resend new OTP
5. http://localhost:9090/forgotPassword         GET                                   Forgot password page
6. http://localhost:9090/sendResetOtp           POST                                  Forgot password page
7. http://localhost:9090/resetPassword          POST                                  Reset password via OTP
=======



# bY UMA
CREATE TABLE users ( userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, fullName VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, otp INT, status VARCHAR(20) DEFAULT 'INACTIVE', otpGeneratedTime DATE );
>>>>>>> 0277d325eaa7821adb55269d8862572a41d72b7e
