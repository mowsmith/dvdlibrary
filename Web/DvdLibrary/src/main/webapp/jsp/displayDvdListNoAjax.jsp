<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">

    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/search">Search</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/stats">Stats</a></li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayDvdListNoAjax">
                            DVD List (No Ajax)
                        </a>
                    </li>
                </ul>    
            </div>

            <a href="displayNewDvdFormNoAjax">Add a DVD</a><br/>
            <hr/>

            <c:forEach var="dvd" items="${dvdList}">
                <s:url value="deleteDvdNoAjax" var="deleteDvdUrl">
                    <s:param name="dvdId" value="${dvd.id}" />
                </s:url>
                <s:url value="displayEditDvdFormNoAjax" var="editContactUrl">
                    <s:param name="dvdId" value="${dvd.id}" />
                </s:url>

                Title: ${dvd.title}<br/>
                MPAA Rating: ${dvd.mpaaRating}<br/>
                Director: ${dvd.director}<br/>
                Studio: ${dvd.studio}<br/>
                User Note: ${dvd.userNote}<br/>
                Release Date: <fmt:formatDate value="${dvd.releaseDate.time}" type="date" dateStyle="short" /><br/>
                <a href="${editContactUrl}">Edit</a> |
                <a href="${deleteDvdUrl}">Delete</a><br/>
                <c:if test="${''.equals(error)}">${error}</c:if>
                <hr/>
            </c:forEach>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

