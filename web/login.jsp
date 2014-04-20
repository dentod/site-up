<%-- Created by IntelliJ IDEA. --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/login.css">
    <script src="read.js"></script>
    <title>Главная</title>
</head>
<body>
<div id="layer-1">
    <div id="layer-2">
        <form class="form-1" action="j_security_check" method="post">
            <p class="field">
                <input id="login-1" type="text" name="j_username" placeholder="login">
            </p>
            <p class="field">
                <input id="password-1" type="password" name="j_password" placeholder="password">

            </p>
            <p class="submit">
                <input type="submit" class="button" name="submit"  value="log in">
            </p>
        </form>
    </div>
</div>
</body>
</html>