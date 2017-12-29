<%@ page import="rubiks.drools.KieSessionMaker" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="rubiks.js" async></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" async></script>
    <link rel="stylesheet" href="rubiks.css"/>
</head>
    
    <%
        //Set up KieBase so result loads faster
        KieSessionMaker.getKieSession();
    %>
<body>
<c:set var="moves" scope="request" value="${requestScope.movesList}"/>
    <div id="page">
    <div id="rubiksHeader">
        <p>Kaan's Rubik's Cube Solver</p>

        <div id="rubiksSubHeader">
            <p>Is it efficient?: No (work in progress)</p>
            <p>Does it work?: Yes</p>
        </div>
    </div>
    
    <div id="rubiksContainer">
        <div id="cubeLaidOutContainer">
        </div>
        <div id="colourSelection">
            <div id="yellowSelection" class="colourChoice" style="background-color:yellow"></div>       
            <div id="blueSelection" class="colourChoice" style="background-color:blue"></div>
            <div id="whiteSelection" class="colourChoice" style="background-color:white"></div>
            <div id="greenSelection" class="colourChoice" style="background-color:green"></div>
            <div id="redSelection" class="colourChoice" style="background-color:red"></div>
            <div id="orangeSelection" class="colourChoice" style="background-color:orange"></div>
        </div>
        <div id="buttonsContainer">
            <form method="post" name="solveForm" action="/rubiksservlet">
                <input type="hidden" name="cubeInfo"/>
                <input id="solveButton" class="rubiksButton" type="submit" value="Solve" onclick="postJSONCube()"/>
            </form>
            <input id="resetButton"  class="rubiksButton" type="button" value="Reset" onclick="resetFaces()"/>
        </div>

        <div id="answerMoves">
            <c:if test="${empty moves}">
                <p>Cube is not solved</p>
            </c:if>
            <c:forEach var="phase" items="${moves}">
                <c:forEach var="rotation" items="${phase.value}">
                    <c:out value="${rotation}"/><br>
                </c:forEach>
            </c:forEach>
        </div>
    </div>
    </div>

</body>
</html>