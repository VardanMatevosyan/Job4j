<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update</title>
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
            <label for="newUserName">New name</label>
            <input id = "newUserName" type="text" name="newUserName" placeholder="New user name"><br>
            <label for="newUserLogin">New login</label>
            <input id = "newUserLogin" type="text" name="newUserLogin"  placeholder="New user login"><br>
            <label for="selectUserRole">Role</label><br>
            <select id="selectUserRole" size="3" name="newUserRole">
                <p>Chose the role</p><br>
                <c:forEach items="${roles}" var="role">
                    <c:if test="${role.name == 'user'}">
                        <option value="user" selected>user</option>
                    </c:if>
                    <c:if test="${role.name != 'user'}">
                        <option value="${role.name}">${role.name}</option>
                    </c:if>
                </c:forEach>
            </select><br>
            <input type="submit" name="method" value="update">
        </form>
    </div>
    <hr>
    <div class="center">
        <form class="inline" action="store" method="GET">
            <input type="submit" value="create user" name="createUser">
        </form>
        <form class="inline" action="delete" method="GET">
            <input type="submit" value="delete user" name="deleteUser">
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