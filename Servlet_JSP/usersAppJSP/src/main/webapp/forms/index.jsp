<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
    <link rel="stylesheet" href="/users/forms/jspStyle.css">
</head>
<body>
<div class="mainBlock">
    <div>
        <form action="insert" method="POST">
            <label for="userName">Name</label>
            <input id = "userName" type="text" name="user" placeholder="User name"><br>
            <label for="userLogin">Login</label>
            <input id = "userLogin" type="text" name="login"  placeholder="User login"><br>
            <label for="userEmail">Email</label>
            <input id = "userEmail" type="email" name="email"  placeholder="User email"><br>
            <label for="userPassword">Password</label>
            <input id = "userPassword" type="password" name="password"  placeholder="User password"><br>
            <input type="submit" value="create">
        </form>
    </div>
    <hr>
    <div class="center">
        <form class="inline" action="update" method="GET">
            <input type="submit" value="update user" name="updateUser">
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
