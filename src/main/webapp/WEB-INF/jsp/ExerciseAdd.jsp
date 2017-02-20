<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html lang="en">

    <head>
        <title>TrackFit - Add Exercise</title>
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

                <h1 class="title">Add new exercise</h1>
                
                <sf:form method="POST" commandName="exerciseForm" action="/addExercise" id="saveForm">

                    <div class="form-exercise col-md-4">
                        <div class="form-group">

                           <label>Choose exercise:</label>
                           <sf:select path="exerciseID"  placeholder="Chose exercise" class="form-control" required="true">
                                <sf:option value="1" >Back Squat</sf:option>
                                <sf:option value="2" >Bench Press</sf:option>
                                <sf:option value="3" >Deadlift</sf:option>
                                <sf:option value="4" >Front Squat</sf:option>
                                <sf:option value="5" >Power Clean</sf:option>
                                <sf:option value="6" >Push Jerk</sf:option>
                                <sf:option value="7" >Push Press</sf:option>
                                <sf:option value="8" >Shoulder Press</sf:option>
                                <sf:option value="9" >Snatch</sf:option>
                                <sf:option value="10" >Split Jerk</sf:option>
                            </sf:select>

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
                <br>
                <div class="form-exercise col-md-4">
                    <a class="btn btn-block btn-md btn-success" href="/exerciseLog" role="button">Exercise Log</a>
				</div>
				
            </div>
        </main>

        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script>

           $("#saveForm" ).find("input[type='number']").val("");

        </script>

    </body>
</html>
