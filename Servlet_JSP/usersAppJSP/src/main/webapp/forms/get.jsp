<%@ page import="ru.matevosyan.model.User" %>
<%@ page import="java.util.concurrent.CopyOnWriteArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="ru.matevosyan.database.UserStore" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.Writer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Get</title>
    <link href="/users/forms/jspStyle.css" rel="stylesheet">
</head>
<body>
<div>
    <div class="form">
        <%!private static final UserStore userStore = UserStore.getInstance();%>
        <%
            CopyOnWriteArrayList<User> storeResult = UserStore.getInstance().getResult();
            if (!storeResult.isEmpty()) {
                for (User user : storeResult) {
        %>
        <div>
            <p>
                <b>Name:</b> <%=user.getName()%><br>
                <b>Login:</b> <%=user.getLogin()%><br>
                <b>Date:</b> <%=user.getCreateDate()%><br>
            </p>
        </div>
        <hr>
        <%}
        } else {%>
        <pre>
            <b>Can't find any users</b>
        </pre>
        <%}%>
    </div>
</div>
</body>
</html>