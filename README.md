# Maven dependecy tree
mvn dependency:tree > pro.txt



# bY UMA
CREATE TABLE users ( userId INT NOT NULL AUTO_INCREMENT PRIMARY KEY, fullName VARCHAR(100) NOT NULL, email VARCHAR(100) NOT NULL UNIQUE, password VARCHAR(255) NOT NULL, otp INT, status VARCHAR(20) DEFAULT 'INACTIVE', otpGeneratedTime DATE );
