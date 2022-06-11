<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 09.06.22
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</form>
</body>
</html>
