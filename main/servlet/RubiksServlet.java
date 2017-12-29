package servlet;

import org.json.JSONException;
import org.json.JSONObject;
import rubiks.rubikscube.Cube;
import rubiks.rubikscube.CubePiece;
import rubiks.solver.Solver;
import rubiks.solver.RubiksResults;
import rubiks.util.RubiksColour;
import rubiks.util.RubiksCubeException;
import rubiks.util.RubiksCubeValidator;
import rubiks.util.RubiksDirection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Kaan on 2017-08-05.
 */
public class RubiksServlet extends HttpServlet{
    private static final String
        UP = "up",
        DOWN = "down",
        LEFT = "left",
        RIGHT = "right",
        FRONT = "front",
        BACK = "back",
        BOTTOM_LEFT = "bottomLeft",
        BOTTOM_RIGHT = "bottomRight",
        BOTTOM_MID = "bottomMid",
        MID_LEFT = "midLeft",
        MID_RIGHT = "midRight",
        MID_MID = "midMid",
        TOP_LEFT = "topLeft",
        TOP_RIGHT = "topRight",
        TOP_MID = "topMid";
    private JSONObject cubeJSON;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        out.print("<html><h1>THE ANSWER</h1>");
        String cubeInfoString = req.getParameter("cubeInfo");
        try{
            cubeJSON =  new JSONObject(cubeInfoString);
            
            Cube cube = new Cube();


            CubePiece topLeftFront = new CubePiece(CubePiece.CORNER_PIECE);
            topLeftFront.put(RubiksDirection.UP, getColourFromFaceAndPieceLocation(UP,BOTTOM_LEFT));
            topLeftFront.put(RubiksDirection.LEFT, getColourFromFaceAndPieceLocation(LEFT,TOP_RIGHT));
            topLeftFront.put(RubiksDirection.FRONT, getColourFromFaceAndPieceLocation(FRONT,TOP_LEFT));
            cube.setTopLeftFront(topLeftFront);
            
            CubePiece topLeftBack = new CubePiece(CubePiece.CORNER_PIECE);
            topLeftBack.put(RubiksDirection.UP, getColourFromFaceAndPieceLocation(UP,TOP_LEFT));
            topLeftBack.put(RubiksDirection.LEFT, getColourFromFaceAndPieceLocation(LEFT,TOP_LEFT));
            topLeftBack.put(RubiksDirection.BACK, getColourFromFaceAndPieceLocation(BACK,TOP_RIGHT));
            CubePiece topRightBack = new CubePiece(CubePiece.CORNER_PIECE);
            cube.setTopLeftBack(topLeftBack);
            
            topRightBack.put(RubiksDirection.UP, getColourFromFaceAndPieceLocation(UP,TOP_RIGHT));
            topRightBack.put(RubiksDirection.RIGHT, getColourFromFaceAndPieceLocation(RIGHT,TOP_RIGHT));
            topRightBack.put(RubiksDirection.BACK,getColourFromFaceAndPieceLocation(BACK,TOP_LEFT));
            cube.setTopRightBack(topRightBack);
            
            CubePiece topRightFront = new CubePiece(CubePiece.CORNER_PIECE);
            topRightFront.put(RubiksDirection.FRONT, getColourFromFaceAndPieceLocation(FRONT,TOP_RIGHT));
            topRightFront.put(RubiksDirection.UP, getColourFromFaceAndPieceLocation(UP,BOTTOM_RIGHT));
            topRightFront.put(RubiksDirection.RIGHT,getColourFromFaceAndPieceLocation(RIGHT,TOP_LEFT));
            cube.setTopRightFront(topRightFront);

            CubePiece bottomLeftFront = new CubePiece(CubePiece.CORNER_PIECE);
            bottomLeftFront.put(RubiksDirection.FRONT, getColourFromFaceAndPieceLocation(FRONT,BOTTOM_LEFT));
            bottomLeftFront.put(RubiksDirection.LEFT, getColourFromFaceAndPieceLocation(LEFT,BOTTOM_RIGHT));
            bottomLeftFront.put(RubiksDirection.DOWN, getColourFromFaceAndPieceLocation(DOWN,TOP_LEFT));
            cube.setBottomLeftFront(bottomLeftFront);


            CubePiece bottomLeftBack = new CubePiece(CubePiece.CORNER_PIECE);
            bottomLeftBack.put(RubiksDirection.LEFT, getColourFromFaceAndPieceLocation(LEFT,BOTTOM_LEFT));
            bottomLeftBack.put(RubiksDirection.BACK, getColourFromFaceAndPieceLocation(BACK,BOTTOM_RIGHT));
            bottomLeftBack.put(RubiksDirection.DOWN, getColourFromFaceAndPieceLocation(DOWN,BOTTOM_LEFT));
            cube.setBottomLeftBack(bottomLeftBack);

            CubePiece bottomRightBack = new CubePiece(CubePiece.CORNER_PIECE);
            bottomRightBack.put(RubiksDirection.RIGHT, getColourFromFaceAndPieceLocation(RIGHT,BOTTOM_RIGHT));
            bottomRightBack.put(RubiksDirection.BACK, getColourFromFaceAndPieceLocation(BACK,BOTTOM_LEFT));
            bottomRightBack.put(RubiksDirection.DOWN, getColourFromFaceAndPieceLocation(DOWN,BOTTOM_RIGHT));
            cube.setBottomRightBack(bottomRightBack);

            CubePiece bottomRightFront = new CubePiece(CubePiece.CORNER_PIECE);
            bottomRightFront.put(RubiksDirection.FRONT, getColourFromFaceAndPieceLocation(FRONT,BOTTOM_RIGHT));
            bottomRightFront.put(RubiksDirection.RIGHT, getColourFromFaceAndPieceLocation(RIGHT,BOTTOM_LEFT));
            bottomRightFront.put(RubiksDirection.DOWN, getColourFromFaceAndPieceLocation(DOWN,TOP_RIGHT));
            cube.setBottomRightFront(bottomRightFront);

            //Top Edges
            CubePiece topFront = new CubePiece(CubePiece.EDGE_PIECE);
            topFront.put(RubiksDirection.FRONT, getColourFromFaceAndPieceLocation(FRONT,TOP_MID));
            topFront.put(RubiksDirection.UP, getColourFromFaceAndPieceLocation(UP,BOTTOM_MID));
            cube.setTopFront(topFront);

            CubePiece topLeft = new CubePiece(CubePiece.EDGE_PIECE);
            topLeft.put(RubiksDirection.LEFT, getColourFromFaceAndPieceLocation(LEFT,TOP_MID));
            topLeft.put(RubiksDirection.UP, getColourFromFaceAndPieceLocation(UP,MID_LEFT));
            cube.setTopLeft(topLeft);

            CubePiece topBack = new CubePiece(CubePiece.EDGE_PIECE);
            topBack.put(RubiksDirection.BACK, getColourFromFaceAndPieceLocation(BACK,TOP_MID));
            topBack.put(RubiksDirection.UP, getColourFromFaceAndPieceLocation(UP,TOP_MID));
            cube.setTopBack(topBack);

            CubePiece topRight = new CubePiece(CubePiece.EDGE_PIECE);
            topRight.put(RubiksDirection.RIGHT, getColourFromFaceAndPieceLocation(RIGHT,TOP_MID));
            topRight.put(RubiksDirection.UP, getColourFromFaceAndPieceLocation(UP,MID_RIGHT));
            cube.setTopRight(topRight);

            //Bottom Edges
            CubePiece bottomFront = new CubePiece(CubePiece.EDGE_PIECE);
            bottomFront.put(RubiksDirection.DOWN, getColourFromFaceAndPieceLocation(DOWN,TOP_MID));
            bottomFront.put(RubiksDirection.FRONT, getColourFromFaceAndPieceLocation(FRONT,BOTTOM_MID));
            cube.setBottomFront(bottomFront);

            CubePiece bottomLeft = new CubePiece(CubePiece.EDGE_PIECE);
            bottomLeft.put(RubiksDirection.DOWN, getColourFromFaceAndPieceLocation(DOWN,MID_LEFT));
            bottomLeft.put(RubiksDirection.LEFT, getColourFromFaceAndPieceLocation(LEFT,BOTTOM_MID));
            cube.setBottomLeft(bottomLeft);

            CubePiece bottomBack = new CubePiece(CubePiece.EDGE_PIECE);
            bottomBack.put(RubiksDirection.DOWN, getColourFromFaceAndPieceLocation(DOWN,BOTTOM_MID));
            bottomBack.put(RubiksDirection.BACK, getColourFromFaceAndPieceLocation(BACK,BOTTOM_MID));
            cube.setBottomBack(bottomBack);

            CubePiece bottomRight = new CubePiece(CubePiece.EDGE_PIECE);
            bottomRight.put(RubiksDirection.DOWN, getColourFromFaceAndPieceLocation(DOWN,MID_RIGHT));
            bottomRight.put(RubiksDirection.RIGHT, getColourFromFaceAndPieceLocation(RIGHT,BOTTOM_MID));
            cube.setBottomRight(bottomRight);

            //Middle Edges
            CubePiece frontLeft = new CubePiece(CubePiece.EDGE_PIECE);
            frontLeft.put(RubiksDirection.FRONT, getColourFromFaceAndPieceLocation(FRONT,MID_LEFT));
            frontLeft.put(RubiksDirection.LEFT, getColourFromFaceAndPieceLocation(LEFT,MID_RIGHT));
            cube.setLeftFront(frontLeft);

            CubePiece backLeft = new CubePiece(CubePiece.EDGE_PIECE);
            backLeft.put(RubiksDirection.BACK, getColourFromFaceAndPieceLocation(BACK,MID_RIGHT));
            backLeft.put(RubiksDirection.LEFT, getColourFromFaceAndPieceLocation(LEFT, MID_LEFT));
            cube.setLeftBack(backLeft);


            CubePiece backRight = new CubePiece(CubePiece.EDGE_PIECE);
            backRight.put(RubiksDirection.RIGHT, getColourFromFaceAndPieceLocation(RIGHT,MID_RIGHT));
            backRight.put(RubiksDirection.BACK, getColourFromFaceAndPieceLocation(BACK,MID_LEFT));
            cube.setRightBack(backRight);

            CubePiece frontRight = new CubePiece(CubePiece.EDGE_PIECE);
            frontRight.put(RubiksDirection.RIGHT, getColourFromFaceAndPieceLocation(RIGHT,MID_LEFT));
            frontRight.put(RubiksDirection.FRONT, getColourFromFaceAndPieceLocation(FRONT,MID_RIGHT));
            cube.setRightFront(frontRight);

            CubePiece front = new CubePiece(CubePiece.CENTER_PIECE);
            front.put(RubiksDirection.FRONT, getColourFromFaceAndPieceLocation(FRONT, MID_MID));
            cube.setFront(front);

            CubePiece left = new CubePiece(CubePiece.CENTER_PIECE);
            left.put(RubiksDirection.LEFT, getColourFromFaceAndPieceLocation(LEFT, MID_MID));
            cube.setLeft(left);

            CubePiece back = new CubePiece(CubePiece.CENTER_PIECE);
            back.put(RubiksDirection.BACK, getColourFromFaceAndPieceLocation(BACK, MID_MID));
            cube.setBack(back);

            CubePiece right = new CubePiece(CubePiece.CENTER_PIECE);
            right.put(RubiksDirection.RIGHT, getColourFromFaceAndPieceLocation(RIGHT,MID_MID));
            cube.setRight(right);

            CubePiece top = new CubePiece(CubePiece.CENTER_PIECE);
            top.put(RubiksDirection.UP, getColourFromFaceAndPieceLocation(UP,MID_MID));
            cube.setTop(top);

            CubePiece bottom = new CubePiece(CubePiece.CENTER_PIECE);
            bottom.put(RubiksDirection.DOWN, getColourFromFaceAndPieceLocation(DOWN,MID_MID));
            cube.setBottom(bottom);
            RubiksResults results = new RubiksResults();

            new Solver(cube,results,15000).solveCube();

            req.setAttribute("movesList", results);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/");
            dispatcher.forward(req,resp);

//            for(List<String> moveList : results.values()){
//                for(String move: moveList) out.print("<br>"+ move);
//            }

        }catch (Exception e){
            out.print("<h3>"+e+"</h3>");
        }
        out.print("</html>");
    }

    private RubiksColour getColourFromFaceAndPieceLocation(String faceLocation, String pieceLocation) throws JSONException{
        JSONObject face = (JSONObject) cubeJSON.get(faceLocation);
        String colour = (String) face.get(pieceLocation);
        return getColour(colour);
    }

    private RubiksColour getColour(String colour){
        for(RubiksColour rubiksColour : RubiksColour.values()){
            if(colour.toUpperCase().equals(rubiksColour.toString())){
                return rubiksColour;
            }
        }
        return RubiksColour.NO_COLOUR;
    }
}
