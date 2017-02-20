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

                <h2 class="title">Goal ${goal[0]}</h2>

                <div class="goal-box">
                    <p><label>Exercise:</label>
                        <span>${goal[1]}</span></p>
                    <p><label>Type:</label>
                        <span>${goal[7]}</span></p>
                    <p><label>Start:</label>
                        <span>${goal[4]}</span></p>
                    <p><label>End</label>
                        <span>${goal[5]}</span></p>

                    <p><label>Reps:</label>
                        <span>${goal[3]}</span></p>
                    <p><label>Kilograms: </label>
                        <span>${goal[2]}</span></p>
                    <p><label>Status:</label>
                        <span>${goal[8]}</span></p>
                </div>
                <br><br>
                <sf:form method="POST" commandName="exerciseForm" action="/addExerciseGoal" id="saveForm">

                    <div class="form-exercise col-md-4">
                        <div class="form-group">

                            <label style="color:grey">Add exercise to goal</label>
                            <br>
                            <label>Units:</label>
                            <div>
                                <sf:input path="unit1" type="number" placeholder="kg" min="0" max="1000" class="form-control" required="true" style="width:33%;  float: left;  margin-right: 5px;  }"/>
                                <span  style="float: left; margin: 0px 5px 0px 1px;">x</span>
                                <sf:input path="unit2" type="number" placeholder="reps" min="0" max="1000" class="form-control" required="true" style="width:33%; float: left; "/>
                           </div>

                        </div>
                        <br><br>
                        <div class="form-group">
                            <input type="submit" VALUE="Save Exercise" class="btn btn-primary btn-lg btn-block"/>
                        </div>
                    </div>
                </sf:form>

            </div>
        </main>

        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script>
             $("#saveForm" ).find("input[type='number']").val("");
        </script>

    </body>
</html>
