<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib  prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<jsp:include page="/hello?command=init" />
    <head>
        <title>Signing up</title>
        <link rel="stylesheet" href="../css/style.css" type="text/css">
    </head>

    <body>
    <header>
        <p>Company</p>

        <c:if test="${user == null}">
            <p><a class="registration" href="/jsp/signin.jsp">Sign in</a></p>
        </c:if>
        <c:if test="${user == null}">
            <p><a class="registration" href="/jsp/signup.jsp">Sign up</a></p>
        </c:if>

        <p><c:out value="${user.firstName}"/></p>

        <c:if test="${user != null}">
            <p><a href="/hello?command=signOut">Sign out</a></p>
        </c:if>
    </header>

    <div id="forAdmin">
        <c:if test="${user.firstName eq 'admin'}">
            <a href="/addfilmpage">Add film</a>
        </c:if>
    </div>

    <table>
    <c:forEach var="film" items="${listFilms}">
        <tr>
            <td><c:out value="${film.id}"/></td>
            <td><c:out value="${film.title}"/></td>
            <td><c:out value="${film.description}"/></td>
            <td><c:out value="${film.runtime}"/></td>
            <td><a>Edit</a></td>
            <td><a>Remove</a></td>
        </tr>
    </c:forEach>
    </table>
    </body>
</html>