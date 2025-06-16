<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>ProQuiz-Login</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body>
    <div class="container">
        <h2>Quiz Manager</h2>
        <form action="/verifyLogin" method="post">
            <label>Username:</label>
            <input type="email" name="email" required><br> <br>
            <label>Password:</label>
            <input type="password" name="password" required><br><br>
            <input id="log" type="submit" value="Login">
        </form>
        <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
        </c:if>
    </div>
</body>
</html>
