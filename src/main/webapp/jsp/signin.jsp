<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 09.06.22
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/hello">
    <input type="hidden" name="command" value="signIn">
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email" required><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br>
    <input type="submit" name = "s1" value="signIn">

    <c:if test="${param.wrong_password == true}">
        <p>Wrong password!</p>
    </c:if>
    <c:if test="${param.user_not_found == true}">
        <p>User wasn't found!</p>
    </c:if>
</form>
</body>
</html>
