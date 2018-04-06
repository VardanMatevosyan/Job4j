<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete</title>
    <style><%@include file="../../style/style.css"%></style>
</head>
<body>
<div class="mainBlock">
    <c:if test="${error != ''}">
        <div style="background-color: red;">
            <c:out value="${error}"/>
        </div>
    </c:if>
    <div class="logOutButton">
        <form action="${pageContext.servletContext.contextPath}/signout" method="post">
            <input id="userLogOut" type="submit" name="method" value="logout">
        </form>
    </div>
    <div>
        <form action="${request.getContextPath()}" method="post">
            <label for="userName">Name</label>
            <input id = "userName" type="text" name="user" placeholder="User name"><br>
            <label for="userLogin">Login</label>
            <input id = "userLogin" type="text" name="login"  placeholder="User login"><br>
            <input type="submit" name="method" value="delete">
        </form>
    </div>
    <hr>
    <div class="center">
        <form class="inline" action="update" method="GET">
            <input type="submit" value="update user" name="updateUser">
        </form>
        <form class="inline" action="store" method="GET">
            <input type="submit" value="create user" name="createUser">
        </form>
    </div>
    <hr>
    <div>
        <br>
        <jsp:include page="get.jsp" flush="true"/>

    </div>
</div>
</body>
</html>