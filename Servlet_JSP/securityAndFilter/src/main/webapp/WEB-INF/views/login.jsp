<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Log in</title>
    <meta charset="UTF-8">
    <style><%@include file="../../style/style.css"%></style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        function validate() {
        var result = true;
        var login = document.getElementsByName("login")[0].value;
        var password = document.getElementsByName("password")[0].value;
        if (login === '' || password === '') {
            result = false
        }
        if (!result) {
            alert('Please correct your data and try again');
          }
        return result;
        }
    </script>
</head>
<body>
<div class="main-wrap">

    <div class="login-main">

        <form action="${pageContext.servletContext.contextPath}/signin" method="post" onsubmit="return validate();">
            <label for="userLogin">
            <input class="box1 border1" id = "userLogin" type="text" name="login"  placeholder="User login"><br>
            </label>
            <label for="userPassword">
            <input class="box1 border2" id = "userPassword" type="password" name="password"  placeholder="User password"><br><br>
            </label>
            <input class="send" type="submit" name="method" value="In">
        </form>
        <c:if test="${error != ''}">
            <div class="error" style="background-color: red;">
                <c:out value="${error}"/>
            </div>
        </c:if>
    </div>
</body>
</html>
