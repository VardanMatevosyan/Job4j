<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%--<c:set var="url">${pageContext.request.requestURL}</c:set>--%>
    <%--<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />--%>
        <style><%@include file="../../style/style.css"%></style>
    <meta charset="UTF-8">
    <title>Index</title>
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
        <form action="insert" method="POST">
            <label for="userName">Name</label>
            <input id = "userName" type="text" name="user" placeholder="User name"><br>
            <label for="userLogin">Login</label>
            <input id = "userLogin" type="text" name="login"  placeholder="User login"><br>
            <label for="userEmail">Email</label>
            <input id = "userEmail" type="email" name="email"  placeholder="User email"><br>
            <label for="userPassword">Password</label>
            <input id = "userPassword" type="password" name="password"  placeholder="User password"><br>
            <label for="selectUserRole">Role</label><br>

            <select id="selectUserRole" size="3" name="userRole">
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
            <input type="submit" name="method" value="create">
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
