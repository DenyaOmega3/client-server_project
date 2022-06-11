<%--
  Created by IntelliJ IDEA.
  User: denyaalpha
  Date: 09.06.22
  Time: 12:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form action="/hello">
        <input type="hidden" name="command" value="signUp">
        <label for="first_name">First name:</label><br>
        <input type="text" id="first_name" name="firstName" required><br>
        <label for="last_name">Last name:</label><br>
        <input type="text" id="last_name" name="lastName" required><br>
        <label for="birth_date">Date of birth:</label><br>
        <input type="date" id="birth_date" name="birthDate" required><br>
        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email" required><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br>
        <input type="submit"  value="signUp">
    </form>
</body>
</html>
