<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en" >

    <head>
        <title>TrackFit</title>
        <%--Bootstrap CSS--%>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
        <%--Local CSS--%>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/trackFitMain.css"/>"/>
    </head>
    <body >
        <main class="container-fluid" style="height:100%">
            <div class="main-container">
                <span style="float:right" >${user}</span>
                <a href="/"><span class="logo glyphicon glyphicon-link" ></span></a>
                <h1>TrackFit</h1>
                <p>The web app that helps you stay fit.</p>
                <ul class="main-btn-ul">
                    <a class="btn btn-block btn-lg btn-danger btn-main" href="/addExercise" role="button">Add Exercise</a>
                </ul>
				<ul class="main-btn-ul">
					<a class="btn btn-block btn-lg btn-danger btn-main" href="/addGoal" role="button">Add Goal</a>
				</ul>
                <ul class="main-btn-ul">
                    <a class="btn btn-block btn-lg btn-danger btn-main" href="/exerciseLog" role="button">Exercise Log</a>
                </ul>
                <ul class="main-btn-ul">
                    <a class="btn btn-block btn-lg btn-danger btn-main" href="/goalLog" role="button">Goal Log</a>
                </ul>
                <ul class="main-btn-ul">
                    <a class="btn btn-block btn-lg btn-danger btn-main" href="/profile" role="button">Profile</a>
                </ul>
                <ul class="main-btn-ul">
                    <a class="btn btn-block btn-lg btn-warning btn-main" href="/logout" role="button">Log Out</a>
                </ul>

            </div>
        </main>
    </body>

</html>
