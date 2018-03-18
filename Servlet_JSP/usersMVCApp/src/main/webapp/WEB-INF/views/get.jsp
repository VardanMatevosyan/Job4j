<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Get</title>
    <style><%@include file="../../style/style.css"%></style>
</head>
<body>
<div>
    <div class="form">
        <c:forEach items="${users}" var="user">
        <div>
            <p>
                <b>Name:</b><c:out value="${user.name}"></c:out><br>
                <b>Login:</b><c:out value="${user.login}"></c:out><br>
                <b>Date:</b><c:out value="${user.createDate}"></c:out><br>
            </p>
        </div>
        <hr>
        </c:forEach>
    </div>
</div>
</body>
</html>