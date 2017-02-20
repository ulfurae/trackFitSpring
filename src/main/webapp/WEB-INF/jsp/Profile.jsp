<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>TrackFit - Profile</title>
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

                <h1 class="title">Profile</h1>
                
        		<table class="userProfile">
        			<tbody>
        				<tr>
        					<td id="userAttr"><b>Name:</b></td>
        					<td>${user.fullName}</td>
        				</tr>
        				<tr>
        					<td id="userAttr"><b>Birthday:</b></td>
        					<td>${newBirthday}</td>
        				</tr>
        				<tr>
        					<td id="userAttr"><b>Height:</b></td>
        					<td>${user.height} cm</td>
        				</tr>
        				<tr>
        					<td id="userAttr"><b>Weight:</b></td>
        					<td>${user.weight} kg</td>
        					<td><a style="margin-left:10px" href="/viewProfile/change">Change weight</a></td>
        				</tr>
        				<tr>
        					<td id="userAttr"><b>BMI:</b></td>
        					<td>${bmi.BMIIndex}</td>
        					<td><span style="font-style:italic"> - ${bmi.idealWeight}</span></td>
        				</tr>
        			</tbody>
        		</table>
        		
            </div>
        </main>


    </body>
</html>
