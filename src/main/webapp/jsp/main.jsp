<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib  prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html>
<jsp:include page="/hello?command=init" />
    <head>
        <title>Signing up</title>
        <link rel="stylesheet" href="../css/style.css" type="text/css">
    </head>

    <body>
    <c:set var = "now" value = "<%=LocalDateTime.now()%>" />
        <header>
            <p>Company</p>

            <c:if test="${user == null}">
                <p><a class="registration" href="/signin">Sign in</a></p>
            </c:if>
            <c:if test="${user == null}">
                <p><a class="registration" href="/signup">Sign up</a></p>
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

    <c:if test="${user.firstName eq 'admin'}">
        <table>
            <tr>
                <td>ID</td>
                <td>Film</td>
                <td>Description</td>
                <td>Total hours</td>
                <td></td>
                <td></td>
            </tr>
        <c:forEach var="film" items="${listFilms}">
            <tr>
                <td><c:out value="${film.id}"/></td>
                <td><c:out value="${film.title}"/></td>
                <td><c:out value="${film.description}"/></td>
                <td><c:out value="${film.runtime}"/></td>

                <td><a href="/chooseHall?film_id=${film.id}">Add session</a></td>
                <td><a href="/updatefilmpage?film_id=${film.id}&title=${film.title}&description=${film.description}&runtime=${film.runtime}">Edit</a></td>
                <td><a href="/hello?command=removeFilm&film_id=${film.id}">Remove</a></td>
            </tr>

        </c:forEach>
            </c:if>

    </table>
    <%--<p><fmt:formatDate type = "date" pattern="dd-MMMM" value = "${now}" /></p> displays format date 13 June--%>
    <p>
    <c:forEach var="i" begin="1" end="7">
        <a href="/hello?command=displaySession&currentDate=${now}">
         ${now.dayOfMonth} ${now.month}
        <c:set var = "now" value = "${now.plusDays(1)}" />
        </a>
    </c:forEach>

        <c:forEach var="film" items="${uniqueFilmList}">
    <h1> ${film.title} </h1>
    <p> Description: ${film.description}</p>
    <p> Runtime ${film.runtime}</p>
    <div class="sessions">
        <c:forEach var="session" items="${sessionList}">
            <c:if test="${session.film.title eq film.title}">
                <div class="place">
                    <div> Time: ${session.date.hour}:00</div>
                    <div> Hall: ${session.hall_id}</div>
                    <a href="/hello?command=getHallInformation&hall_id=${session.hall_id}&session_id=${session.id}">Choose</a>
                    <br/>
                </div>
            </c:if>
        </c:forEach>
    </div>
    </c:forEach>
    </p>
    </body>
</html>