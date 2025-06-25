<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Forgot Password</title>
</head>
<body style="text-align: center;
                 padding: 13% 0% 0% 27%;
                 background-color: darkgray;">
<div  class= "mainContainer" style="background-color: azure;   width: 50%;"><br>
<div class="container" >
    <h2>Forgot Password</h2>
    <form action="/sendResetOtp" method="post" >
        <label>Enter your registered Email:</label>
        <input type="email" name="email" required> <br><br>
          <c:if test="${not empty otpSend}">
                            <p style="color:yellow">${otpSend}</p>
                        </c:if>
                        <c:if test="${not empty error}">
                                <p style="color:red">${error}</p>
                            </c:if>
        <input type="submit" value="Send OTP" style="background-color: green; color: white; text-align: center; width: 35%;padding: 12px;">
    </form>



</div>
<div class="container"  >
    <h2>Reset Your Password</h2>
    <form action="/resetPassword" method="post" >
        <input type="hidden" name="email" value="${email}">
        <label>Enter OTP:</label>
        <input type="text" name="otp" required><br><br>

        <label>New Password:</label>
        <input type="password" name="newPassword" required><br><br>

        <input type="submit" value="Reset Password" style="background-color: green; color: white; text-align: center; width: 35%;padding: 12px;">
    </form><br>

    </div>
</div>
</body>
</html>
