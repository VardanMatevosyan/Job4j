<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Log in</title>
    <style><%@include file="../../style/style.css"%></style>
    <meta charset="UTF-8">
</head>
<body>
<div class="mainBlock">
<c:if test="${error != ''}">
    <div style="background-color: red;">
        <c:out value="${error}"/>
    </div>
</c:if>
<form class="center" action="${pageContext.servletContext.contextPath}/signin" method="post">
    <label for="userLogin">Login</label>
    <input id = "userLogin" type="text" name="login"  placeholder="User login"><br>
    <label for="userPassword">Password</label>
    <input id = "userPassword" type="password" name="password"  placeholder="User password"><br><br>
    <input type="submit" name="method" value="sign in">
</form>
</body>
</html>
