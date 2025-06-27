<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verify OTP</title>
     <link rel="stylesheet" href="/css/verifyOtp.css">
</head>
<body>
 <div class="container">
    <h2 style="color:green;">OTP sent to your email.</h2>
  <c:if test="${not empty otpSend}">
            <p style="color:green">${otpSend}</p>
        </c:if>

 <form action="/resendOtp" method="post" style="margin-top:15px;">
 <p> If not received click here to Resend </p>
        <input type="hidden" name="email" value="${email}">
        <button  id="log" type="submit">Resend OTP</button><br>
    </form> <br>

    <form action="/verifyOtp" method="post">
        <label>Enter OTP:</label>
        <input type="text" name="otp" required><br>
        <input type="hidden" name="email" value="${email}"> <br>
        <input id="log" type="submit" value="submit">
    </form>
   <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>

</div>

</body>
</html>
