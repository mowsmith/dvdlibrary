<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit DVD</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
        <!-- SWC Icon -->
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Edit DVD Form</h1>
            <a href="displayDvdListNoAjax">DVD List (No Ajax)</a><br/>
            <hr/>
            <!--TODO: auto fill rating and release date-->
            <sf:form class="form-horizontal"
                     role="form"
                     action="editDvdNoAjax"
                     method="POST"
                     modelAttribute="dvd">
                <sf:hidden path="id" />
                <div class="form-group">
                    <label for="add-title"
                           class="col-md-4 control-label">Title:</label>
                    <div class="col-md-8">
                        <sf:input path="title"
                                  type="text"
                                  class="form-control"
                                  id="add-title"
                                  name="title"
                                  placeholder="Title"/>
                        <sf:errors path="title" cssClass="error" />
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-director" class="col-md-4 control-label">Director:</label>
                    <div class="col-md-8">
                        <sf:input path="director"
                                  type="text"
                                  class="form-control"
                                  id="add-director"
                                  name="director"
                                  placeholder="Director"/>
                        <sf:errors path="director" cssClass="error" />
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-studio"
                           class="col-md-4 control-label">Studio:</label>
                    <div class="col-md-8">
                        <sf:input path="studio"
                                  type="text"
                                  class="form-control"
                                  id="add-studio"
                                  name="studio"
                                  placeholder="Studio"/>
                        <sf:errors path="studio" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-user-note" class="col-md-4 control-label">Note:</label>
                    <div class="col-md-8">
                        <sf:input path="userNote"
                                  type="text"
                                  class="form-control"
                                  id="add-user-note"
                                  name="userNote"
                                  placeholder="Note"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-rating" class="col-md-4 control-label">MPAA Rating: </label>
                    <div class="col-md-8">
                        <sf:radiobutton path="rating" name="rating" value="G" />G
                        <sf:radiobutton path="rating" name="rating" value="PG" />PG
                        <sf:radiobutton path="rating" name="rating" value="PG13" />PG-13
                        <sf:radiobutton path="rating" name="rating" value="R" />R
                        <sf:radiobutton path="rating" name="rating" value="NC17" />NC17
                        <sf:radiobutton path="rating" name="rating" value="NR" />NR<br>
                        <sf:errors path="mpaaRating" cssClass="error" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-date" class="col-md-4 control-label">Release Date: </label>
                    <div class="col-md-8">
                        <sf:input path="date" type="date" name="date" /><br/>
                        <sf:errors path="releaseDate" cssClass="error" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <button type="submit"
                                    id="add-button"
                                    class="btn btn-default">Edit DVD</button>
                        </div>
                    </div>
            </sf:form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

