<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html/js; charset=UTF-8">
    <style><%@ include file="../../style/style.css"%></style>
    <title>Index</title>
</head>
<body>
<div class="mainBlock">
    <c:if test="${validateError != ''}">
        <div style="background-color: red;">
            <c:out value="${validateError}"/>
        </div>
    </c:if>
    <div class="logOutButton">
        <form action="${pageContext.servletContext.contextPath}/signout" method="post">
            <input id="userLogOut" type="submit" name="method" value="logout">
        </form>
    </div>
    <div>
        <form class="formWithValidation" action="insert" method="POST">
            <label for="userName">Name</label>
            <input class = "field" id = "userName" type="text" name="user" placeholder="User name" required><br>
            <label for="userLogin">Login</label>
            <input class = "field" id = "userLogin" type="text" name="login"  placeholder="User login" required><br>
            <label for="userEmail">Email</label>
            <input class = "field" id = "userEmail" type="email" name="email"  placeholder="User email" required><br>
            <label for="userPassword">Password</label>
            <input class = "field" id = "userPassword" type="password" name="password"  placeholder="User password" required><br>
            <label for="selectUserRole">Role</label><br>
            <select class = "field" id="selectUserRole" name="userRole" required>
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
            <label for="selectUserCountry">Country</label><br>
            <select class = "field" id="selectUserCountry" name="countrySelect" required></select><br>
            <label for="selectUserCity">City</label><br>
            <select class = "field" id="selectUserCity" name="citySelect" required></select><br>

            <label for="inputUserStreet">Street</label><br>
            <input class = "field" type="text" id="inputUserStreet" name="streetSelect" required><br>
            <label for="inputUserHomeNumber">Number</label><br>
            <input class = "field" type="number" id="inputUserHomeNumber" name="homeNumberSelect"
                   min="0" max="1000" placeholder="Home number" required><br>

            <label for="selectMusicTypes">Music prepare</label><br>
            <select class = "field" id="selectMusicTypes" name="musicTypesSelect" size="3" multiple required></select><br>
            <input class="validateBtn" type="submit" name="method" value="create">

        </form>
        <hr>
        <div>
        <form action="uploadFile" method="POST" enctype="multipart/form-data">

            <fieldset class="uploadForm">
                <legend>Upload files</legend>
                <div>
                    <label for="uploadFile">Chouse the audio files:</label>
                    <input id="uploadFile" type="file" name="file" multiple="true"
                           accept=".mp3, .aac, .ogg, .mp4, .wav, audio" required><br>
                </div>
                <div>
                    <input type="submit" value="upload" name="uploadAudioFile">
                </div>
            </fieldset>
        </form>
        </div>
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
        <div>
            <table id="users"></table>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
    <%@ include file="../../jsScripts/loadUserInfo.js"%>
    <%@ include file="../../jsScripts/loadCountryAndCity.js"%>
    <%@ include file="../../jsScripts/loadMusicTypes.js"%>
    <%@ include file="../../jsScripts/validateInputs.js"%>
</script>
</body>
</html>
