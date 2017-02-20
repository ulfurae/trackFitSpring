<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>TrackFit - Profile Change</title>
        <%--Bootstrap CSS--%>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
        <%--Local CSS--%>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/postitnote.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/trackFitMain.css"/>"/>
    </head>
    <body >

        <main class="container-fluid" style="height:100%">
            <div class="main-container">
                <a href="/"><span class="logo glyphicon glyphicon-link" ></span></a>

                <h1 class="title">Change Profile</h1>
        		
        		<sf:form method="POST" commandName="newUser" action="/viewProfile" id="changeForm">

                    <div class="form-exercise col-md-4">
                        <div class="form-group">
                            <label>Weight (kg):</label>
                            <sf:input path="weight" type="number" min="0" max="1000" required="true" placeholder="Enter weight" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <input type="submit" VALUE="Change Weight" class="btn btn-success btn-block"/>
                        </div>
                    </div>
                </sf:form>
            </div>
        </main>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>

    	$("#changeForm").find("input[type='number']").val("");

    </script>
    </body>
</html>
