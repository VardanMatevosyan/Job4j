<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete</title>
    <style><%@include file="../../style/style.css"%></style>
</head>
<body>
<div class="mainBlock">
    <c:if test="${errorInDelete != ''}">
        <div style="background-color: red;">
            <c:out value="${errorInDelete}"/>
        </div>
    </c:if>
    <div class="logOutButton">
        <form action="${pageContext.servletContext.contextPath}/signout" method="post">
            <input id="userLogOut" type="submit" name="method" value="logout">
        </form>
    </div>
    <div>
        <form class="formWithValidation" action="${request.getContextPath()}" method="post">
            <label for="userName">Name</label>
            <input class = "field" id = "userName" type="text" name="user" placeholder="User name" required><br>
            <label for="userLogin">Login</label>
            <input class = "field" id = "userLogin" type="text" name="login"  placeholder="User login" required><br>
            <input class="validateDeleteBtn" type="submit" name="method" value="delete">
        </form>
    </div>
    <hr>
    <div class="center">
        <form class="inline" action="update" method="GET">
            <input type="submit" value="update user" name="updateUser" required>
        </form>
        <form class="inline" action="store" method="GET">
            <input type="submit" value="create user" name="createUser" required>
        </form>
    </div>
    <hr>
    <div>
        <br>
        <div>
            <table id="users"></table>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
    <%@ include file="../../jsScripts/loadUserInfo.js"%>
    <%@ include file="../../jsScripts/validateDeletePage.js"%>
</script>
</body>
</html>