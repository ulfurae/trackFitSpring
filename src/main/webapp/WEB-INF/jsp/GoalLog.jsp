<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>TrackFit - Goal Log</title>
        <%--Bootstrap CSS--%>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
        <%--Local CSS--%>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/trackFitMain.css"/>"/>
    </head>
    <body >

        <main class="container-fluid" style="height:100%">
            <div class="main-container">
                <a href="/"><span class="logo glyphicon glyphicon-link" ></span></a>

                <h1 class="title">Goal Log</h1>
                
                <c:choose>
                    <%--If the model has an attribute with the name `goals`--%>
                    <c:when test="${not empty goals}">
                        <%--Create a table--%>
                        <table class="table">
                        	<thead>
                        		    <td><b>Goal number</b></td>
                        			<td><b>Start</b></td>
                        			<td><b>End</b></td>
                        			<td><b>Exercise</b></td>
                        			<td><b>Kilograms</b></td>
                        			<td><b>Reps</b</td>
                        			<td><b>Status</b></td>

                        	</thead>
							<tbody>
                            <c:forEach var="goal" items="${goals}">
                               <tr onclick="window.location='/goal/${goal[0]}';" class="tr-button" role="button">
                                	<td>${goal[0]}</td>
                                    <td>${goal[4]}</td>
                                    <td>${goal[5]}</td>
                                    <td>${goal[1]}</td>
                                    <td>${goal[2]}</td>
                                    <td>${goal[3]}</td>
                                    <td>${goal[8]}</td>
                               </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:when>

                    <%--If all tests are false, then do this--%>
                    <c:otherwise>
                        <h3>You have not made any goals. Where is the ambition man? </h3>
                    </c:otherwise>
                </c:choose>

            </div>
        </main>


    </body>
</html>
