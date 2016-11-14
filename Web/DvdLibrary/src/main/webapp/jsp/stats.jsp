<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Stats</title>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/stats">Stats</a></li>
                    <!--                    <li role="presentation">
                                            <a href="${pageContext.request.contextPath}/displayDvdListNoAjax">
                                                DVD List (No Ajax)
                                            </a>
                                        </li>-->
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-12">
                    <h2>Statistics</h2>
                    <div id="chart_div"></div>
                </div>
            </div> 
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script type="text/javascript" src="http://www.google.com/jsapi"></script>
        <script>
            google.load('visualization', '1.0', {'packages': ['corechart']});
        </script> 
        <script src="${pageContext.request.contextPath}/js/jquery-2.2.4.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/stats.js"></script>

    </body>
</html>

