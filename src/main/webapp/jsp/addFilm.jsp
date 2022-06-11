<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 10.06.22
  Time: 14:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/hello">
        <input type="hidden" name="command" value="addFilm">
        <label for="title">Title:</label><br>
        <input type="text" id="title" name="title" required><br>
        <label for="description">Description:</label><br>
        <input type="text" id="description" name="description" required><br>
        <label for="runtime">Runtime (in seconds):</label><br>
        <input type="text" id="runtime" name="runtime" required><br>
        <input type="submit" name = "s1" value="signIn">
    </form>
</body>
</html>
