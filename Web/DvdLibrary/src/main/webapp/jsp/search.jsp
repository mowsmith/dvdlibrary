<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search</title>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/search">Search</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/stats">Stats</a></li>
                </ul> 
            </div>
            <div class ="row">
                <div class="col-md-6">
                    <h2>Search Results</h2>
                    <%@include file="dvdSummaryTableFragment.jsp"%>
                </div>

                <div class="col-md-6">
                    <h2>Search DVDs</h2>
                    <form class="form-horizontal"
                          role="form"
                          method="POST">
                        <div class="form-group">
                            <label for="search-title"
                                   class="col-md-4 control-label">Title:</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="search-title"
                                       name="title"
                                       placeholder="Title"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="search-director" class="col-md-4 control-label">Director:</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="search-director"
                                       name="director"
                                       placeholder="Director"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="search-studio"
                                   class="col-md-4 control-label">Studio:</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="search-studio"
                                       name="studio"
                                       placeholder="Studio"/>
                            </div>
                        </div>
<!--                        <div class="form-group">
                            <label for="search-user-note" class="col-md-4 control-label">Note:</label>
                            <div class="col-md-8">
                                <input type="text"
                                       class="form-control"
                                       id="search-user-note"
                                       name="userNote"
                                       placeholder="Note"/>
                            </div>
                        </div>-->
                        <div class="form-group">
                            <label for="search-rating" class="col-md-4 control-label">MPAA Rating: </label>
                            <div class="col-md-8">
                                <input type="radio" name="mpaaRating" value="G" id="G">G
                                <input type="radio" name="mpaaRating" value="PG" id="PG">PG
                                <input type="radio" name="mpaaRating" value="PG13" id="PG13">PG-13
                                <input type="radio" name="mpaaRating" value="R" id="R">R
                                <input type="radio" name="mpaaRating" value="NC17" id="NC17">NC17
                                <input type="radio" name="mpaaRating" value="NR" id="NR">NR<br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="search-date" class="col-md-4 control-label">Release Date: </label>
                            <div class="col-md-8">
                                <input type="date" name="releaseDate" id="search-releaseDate">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit"
                                            id="search-button"
                                            class="btn btn-default">Search DVD</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

            </div>
            <%@include file="detailsEditModalFragment.jsp"%>
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/dvdList.js"></script>
    </body>
</html>

