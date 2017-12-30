<%@ page import="rubiks.drools.KieSessionMaker" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script type="text/javascript" src="rubiks.js" async></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" async></script>
    <link rel="stylesheet" href="rubiks.css"/>
    <link rel="icon" href="img/rubik_s_cube_mixed.ico"/>
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
            <div id="github">
                <a href="https://github.com/kalsan1998/RubiksSolver"><img id="githubIcon" src="img/github-2048-black.png"/>GitHub Link</a>
            </div>

            <div id="rubiksSubHeader">
                <p>Is it efficient?: No (work in progress)</p>
                <p>Does it work?: Yes</p>
            </div>
        </div>
        <div id="info">
            <div id="instructions">
                <h1>Instructions</h1>
                <p>
                    1) Click on the colour palette below to select a colour <br>
                    2) Click on the tiles to change their colour to the selected colour<br>
                    3) Input the cube you wish to solve, then click the solve button<br>
                    <h3>NOTE: Make sure the inputted cube is a possible permutation for a Rubik's Cube</h3>
                </p>
            </div>
            <div id="notation">
                <h1>Notation</h1>
                <h3>NOTE: Orientation is relative to the inputted cube. For the default cube, the <br>
                    White face is considered the front, Green is the right, Orange is the bottom</h3>

                <p>
                    U = Rotate Top Face Clockwise<br>
                    D = Rotate Bottom Face Clockwise<br>
                    L = Rotate Left Face Clockwise<br>
                    R = Rotate Right Face Clockwise<br>
                    F = Rotate Front Face Clockwise<br>
                    B = Rotate Front Face Clockwise<br><br>
                    If the letter is followed by <h2 class="keyNotation"><u>'</u></h2>, then the rotation is counter clockwise instead.<br>
                    If the letter is followed by <h2 class="keyNotation"><u>2</u></h2>, then the do 2 rotations instead.
                </p>
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

        <div id="answerContainer">
            <p class="answerHeader">Directions will be displayded here:</p>
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