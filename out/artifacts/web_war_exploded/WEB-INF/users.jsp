<!DOCUMENT HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../css/main.css">
    <link rel="stylesheet" type="text/css" href="../css/users.css">
    <title>Users</title>
    <script src="js/read.js"></script>
</head>
<body>
<div id="layer-1">
    <table id="users-1">
        <tr>
            <th><a>Email</a></th>
            <th>First Name</th>
                <th>
                    Last Name
                </th>

        </tr>
        <c:if test="${not empty users}">
            <c:forEach items="${users}" var="user">
                <tr>


                    <my:security allowRoles="admin"><td><a id="delete"href="delete?email=${user.email}">x</a><a href="edit?email=${user.email}">${user.email}</a></td></my:security>
                    <my:security allowRoles="moder"><td><a>${user.email}</a></td></my:security>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>

                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
<div id="layer-3">

    <form action="/save" method="get">
        <button id="button-2" type="submit" name="button">Exit</button>
    </form>
    <my:security allowRoles="admin">
        <form action="/create" method="post">
        <button id="button-3" type="submit" name="button">Create user</button>
    </form>
    </my:security>

</div>
</body>
</html>