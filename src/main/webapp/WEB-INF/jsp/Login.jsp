<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>TrackFit - login</title>
        <%--Bootstrap CSS--%>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
        <%--Local CSS--%>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/trackFitMain.css"/>"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/signin.css"/>"/>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    </head>

    <body>

        <main class="container-fluid" style="height:100%">
            <div class="main-container">
                <a href="/"><span class="logo glyphicon glyphicon-link" ></span></a>
                <h1>TrackFit</h1>
                <p>The web app that helps you stay fit.</p>

                <div style="display: inline-block;">
                    <form name="login" action="/login" method="POST" class="form-signin">
                        <h4 style="text-align: left;">Log in</h4>
                        <c:if test="${fn:length(loginErrorMessage) > 0}">
                            <div class="alert alert-danger"><strong>Woops!</strong> ${loginErrorMessage}</div>
                        </c:if>
                        <input type="text" name="username" required="true" class="form-control" placeholder="Username" >
                        <input type="password" name="password" required="true" class="form-control" placeholder="Password">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
                    </form>

                    <sf:form commandName="regForm" action="/process-register"  method="POST" class="form-signin" id="signUpForm">
                        <h3 >New to TrackFit? Sign up!</h3>
                        <c:if test="${fn:length(registerErrorMessage) > 0}">
                            <div class="alert alert-danger"><strong>Oh snap!</strong> ${registerErrorMessage}</div>
                        </c:if>

                        <sf:input type="text" path="username" required="true" class="form-control" placeholder="Username" />
                        <sf:input type="password" path="pass"  required="true" class="form-control" placeholder="Password" />
                        <sf:input type="text" path="fullName" required="true" class="form-control" placeholder="Full name" />
                        <sf:input type="text" id="datepicker" required="true" path="birthday" class="form-control" placeholder="Birthday" />
                        <sf:input type="number" path="height" required="true" min="0" max="300" class="form-control" placeholder="Height (cm)" />
                        <sf:input type="number" path="weight" required="true" min="0" max="1000" class="form-control" placeholder="Weight (kg)" />

                        <button class="btn btn-lg btn-success btn-block" type="submit">Sign up</button>
                    </sf:form>
                </div>

            </div>
        </main>

        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>

             $("#datepicker" ).datepicker();

             $("#signUpForm" ).find("input").val("");

        </script>
    </body>
</html>
