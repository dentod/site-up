<!DOCUMENT HTML>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
	<head>
	<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="../css/main.css">
		<link rel="stylesheet" type="text/css" href="../css/createuser.css">
		<title>Create user.</title>
	</head>
	<body>
	<div id="layer-1">
        <form id="create"  action="/save" method="post">
	<div id="layer-2">
        <input type="hidden" name="oldEmail" value="${user.email}"/>
	 <input id="input-1" type="text" name="login" value="${user.email}">
 	 <input id="input-2" type="text" name="password" value="${user.password}">
 	 <input id="input-3" type="text" name="firstName" value="${user.firstName}">
	 <input id="input-4" type="text" name="lastName" value="${user.lastName}">
        <my:security allowRoles="admin"><select id="input-5" name="role">
            <option>
                <c:choose>
                    <c:when test="${user.role == 'admin'}">${user.role}</c:when>
                    <c:when test="${user.role == 'moder'}">${user.role}</c:when>
                    <c:otherwise>
                        moder
                    </c:otherwise>
                </c:choose>
            </option>
            <option>
            <c:choose>
                <c:when test="${user.role == 'admin'}">moder</c:when>
                <c:when test="${user.role == 'moder'}">admin</c:when>
                <c:otherwise>
                    admin
                </c:otherwise>
            </c:choose>
            </option>
        </select></my:security>
	 <p id="text-1">Login :</p>
	 <p id="text-2">Password :</p>
	 <p id="text-3">First Name :</p>
	 <p id="text-4">Last Name :</p>
     <p id="text-5">Role :</p>
	</div>
            <div id="layer-4">
            <button id="button-3" type="submit" name="button">Save</button>
                </div>
        </form>



	</div>
    <div id="layer-5">
        <form id="button-all" method="get" action="/users">
            <button id="button-2" type="submit" name="button">Back</button>
        </form>
    </div>
	</body>
</html>