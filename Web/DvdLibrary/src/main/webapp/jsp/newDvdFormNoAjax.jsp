<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Company Contacts</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>New DVD Form</h1>
            <a href="displayDvdListNoAjax">DVD List (No Ajax)</a><br/>
            <hr/>
            <!--TODO: Make spring form-->
            <form class="form-horizontal"
                  role="form"
                  action="addNewDvdNoAjax"
                  method="POST">
                <div class="form-group">
                    <label for="add-title"
                           class="col-md-4 control-label">Title:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-title"
                               name="title"
                               placeholder="Title"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-director" class="col-md-4 control-label">Director:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-director"
                               name="director"
                               placeholder="Director"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-studio"
                           class="col-md-4 control-label">Studio:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-studio"
                               name="studio"
                               placeholder="Studio"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-user-note" class="col-md-4 control-label">Note:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-user-note"
                               name="userNote"
                               placeholder="Note"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-rating" class="col-md-4 control-label">MPAA Rating: </label>
                    <div class="col-md-8">
                        <input type="radio" name="mpaaRating" value="G" checked>G
                        <input type="radio" name="mpaaRating" value="PG">PG
                        <input type="radio" name="mpaaRating" value="PG13">PG-13
                        <input type="radio" name="mpaaRating" value="R">R
                        <input type="radio" name="mpaaRating" value="NC17">NC17
                        <input type="radio" name="mpaaRating" value="NR">NR<br>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-date" class="col-md-4 control-label">Release Date: </label>
                    <div class="col-md-8">
                        <input type="date" name="releaseDate">
                        <c:if test="${''.equals(error)}">${error}</c:if>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit"
                                id="add-button"
                                class="btn btn-default">Add New DVD</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

