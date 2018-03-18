<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update</title>
    <style><%@include file="../../style/style.css"%></style>
</head>
<body>
<div class="mainBlock">
    <div>
        <form action="update" method="post">
            <label for="userName">Name</label>
            <input id = "userName" type="text" name="user" placeholder="User name"><br>
            <label for="userLogin">Login</label>
            <input id = "userLogin" type="text" name="login"  placeholder="User login"><br>
            <label for="newUserName">New name</label>
            <input id = "newUserName" type="text" name="newUserName" placeholder="New user name"><br>
            <label for="newUserLogin">New login</label>
            <input id = "newUserLogin" type="text" name="newUserLogin"  placeholder="New user login"><br>
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