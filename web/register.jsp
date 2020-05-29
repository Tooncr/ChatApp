<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Register new user" />
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Register new User" />
</jsp:include>
<div>
    <form action="?action=r" id="user-signup" method="post">
        <p>
            <label for="firstName">Your last name:</label>
            <input type="text" id="firstName" name="firstName">
        </p>
        <p>
            <label for="lastName">Your first name:</label>
            <input type="text" id="lastName" name="lastName">
        </p>
        <p>
            <label for="email">Your email </label>
            <input type="text" id="email" name="email">
        </p>
        <p>
            <label for="password">Your password</label>
            <input type="password" id="password" name="password">
        </p>
        <p>
            <label for="passwordConf">Confirm password</label>
            <input type="password" id="passwordConf" name="passwordConf">
        </p>
        <p>
            <input type="submit" value="Register" id="registerbutton" >
        </p>
        <p id="signupresponse"></p>
    </form>

</div>

<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/register.js"></script>

</body>
</html>