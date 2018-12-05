<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign in</title>
    <%--<!-- Required meta tags -->--%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

   <%--Bootstrap CSS--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style><%@include file="../../css/style.css"%></style>
</head>
<body>

<div class="container center-block" id="signInMainContainer">
    <%@include file="signUp.html"%>
    <form method="POST" action="signIn" class="form-signIn" role="form" >
        <div class="form-group">
            <h2 class="form-signin-heading">Please sign in</h2>
            <label for="inputName">User name</label>
            <input id="inputName" name="username" type="text" class="form-control" placeholder="User name"  autofocus=""  aria-describdby="userNameHelpSignIn" required>
            <small id="userNameHelpSignIn" class="form-text text-muted">Enter your name</small>
        </div>
        <div class="form-group">
            <label for="inputPassword">User password</label>
            <input id="inputPassword" name="password" type="password" class="form-control" placeholder="Password" required>
            <small id="userPasswordHelpSignIn" class="form-text text-muted">Password should contains 8 simbols</small>
        </div>
        <%--<с:if test="${userCredential != ''}">--%>
            <%--<div class="errorUserCredential container center-block">--%>
                <%--<span><с:out value="${userCredential}"/></span>--%>
            <%--</div>--%>
        <%--</с:if>--%>
        <с:if test="${error != ''}">
            <div class="container center-block errorUserCredential" >
                <span><с:out value="${error}"/></span>
            </div>
        </с:if>
        <с:if test="${logout != ''}">
            <div class="container center-block errorUserCredential">
                <span><с:out value="${logout}"/></span>
            </div>
        </с:if>
        <button class="btn btn-lg btn-block btn-primary" type="submit">Sign in</button>
    </form>
    <div>
        <a id="modalSignUpInSignIn" data-toggle="modal" data-target="#modalSignUpWindow">sign up</a>
        <a href="${pageContext.request.contextPath}">Main</a>
    </div>
    <%--<button class="form-text text-muted" id="modalSignUpInSignIn" data-toggle="modal" data-target="#modalSignUpWindow">sign up</button>--%>
</div>

<%--<!-- Optional JavaScript -->--%>
<%--<!-- jQuery first, then Popper.js, then Bootstrap JS -->--%>
<%--<!-- jQuery library -->--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%--<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
    <%@include file="../../js/loadImagePrewiev.js"%>
    <%@include file="../../js/modalWindow.js"%>
    <%@include file="../../js/signUpInfoToJson.js"%>
    <%@include file="../../js/hiddenErrorMessages.js"%>
    <%@include file="../../js/validateSignUpInputFields.js"%>
</script>

</body>
</html>