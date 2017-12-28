package rubiks;

import rubiks.rubikscube.Cube;
import rubiks.rubikscube.CubePiece;
import rubiks.util.RubiksColour;
import rubiks.util.RubiksDirection;

/**
 * Created by Kaan Alsan on 7/1/2017.
 */
public class TestingCube extends Cube {
    private static final RubiksDirection UP = RubiksDirection.UP;
    private static final RubiksDirection DOWN = RubiksDirection.DOWN;
    private static final RubiksDirection LEFT = RubiksDirection.LEFT;
    private static final RubiksDirection RIGHT = RubiksDirection.RIGHT;
    private static final RubiksDirection FRONT = RubiksDirection.FRONT;
    private static final RubiksDirection BACK = RubiksDirection.BACK;

    public TestingCube(int i)  {
        try {
            if (i == 1) {
                CubePiece topLeftFront = new CubePiece(3);
                topLeftFront.put(UP, RubiksColour.RED);
                topLeftFront.put(LEFT, RubiksColour.WHITE);
                topLeftFront.put(FRONT, RubiksColour.GREEN);
                CubePiece topLeftBack = new CubePiece(3);
                topLeftBack.put(UP, RubiksColour.WHITE);
                topLeftBack.put(LEFT, RubiksColour.ORANGE);
                topLeftBack.put(BACK, RubiksColour.BLUE);
                CubePiece topRightBack = new CubePiece(3);
                topRightBack.put(UP, RubiksColour.WHITE);
                topRightBack.put(RIGHT, RubiksColour.RED);
                topRightBack.put(BACK, RubiksColour.BLUE);
                CubePiece topRightFront = new CubePiece(3);
                topRightFront.put(FRONT, RubiksColour.GREEN);
                topRightFront.put(UP, RubiksColour.ORANGE);
                topRightFront.put(RIGHT, RubiksColour.WHITE);
                CubePiece[] topCorners = new CubePiece[]{topLeftFront, topLeftBack, topRightBack, topRightFront};
                setTopCorners(topCorners);

                CubePiece bottomLeftFront = new CubePiece(3);
                bottomLeftFront.put(FRONT, RubiksColour.RED);
                bottomLeftFront.put(LEFT, RubiksColour.YELLOW);
                bottomLeftFront.put(DOWN, RubiksColour.BLUE);
                CubePiece bottomLeftBack = new CubePiece(3);
                bottomLeftBack.put(LEFT, RubiksColour.GREEN);
                bottomLeftBack.put(BACK, RubiksColour.YELLOW);
                bottomLeftBack.put(DOWN, RubiksColour.RED);
                CubePiece bottomRightBack = new CubePiece(3);
                bottomRightBack.put(RIGHT, RubiksColour.BLUE);
                bottomRightBack.put(BACK, RubiksColour.ORANGE);
                bottomRightBack.put(DOWN, RubiksColour.YELLOW);
                CubePiece bottomRightFront = new CubePiece(3);
                bottomRightFront.put(FRONT, RubiksColour.YELLOW);
                bottomRightFront.put(RIGHT, RubiksColour.ORANGE);
                bottomRightFront.put(DOWN, RubiksColour.GREEN);
                CubePiece[] bottomCorners = new CubePiece[]{bottomLeftFront, bottomLeftBack, bottomRightBack, bottomRightFront};
                setBottomCorners(bottomCorners);

                //Top Edges
                CubePiece topFront = new CubePiece(2);
                topFront.put(FRONT, RubiksColour.YELLOW);
                topFront.put(UP, RubiksColour.ORANGE);
                CubePiece topLeft = new CubePiece(2);
                topLeft.put(LEFT, RubiksColour.YELLOW);
                topLeft.put(UP, RubiksColour.GREEN);
                CubePiece topBack = new CubePiece(2);
                topBack.put(BACK, RubiksColour.RED);
                topBack.put(UP, RubiksColour.GREEN);
                CubePiece topRight = new CubePiece(2);
                topRight.put(RIGHT, RubiksColour.WHITE);
                topRight.put(UP, RubiksColour.GREEN);
                CubePiece[] topEdges = new CubePiece[]{topFront, topLeft, topBack, topRight};
                setTopEdges(topEdges);

                //Bottom Edges
                CubePiece bottomFront = new CubePiece(2);
                bottomFront.put(DOWN, RubiksColour.WHITE);
                bottomFront.put(FRONT, RubiksColour.ORANGE);
                CubePiece bottomLeft = new CubePiece(2);
                bottomLeft.put(DOWN, RubiksColour.RED);
                bottomLeft.put(LEFT, RubiksColour.WHITE);
                CubePiece bottomBack = new CubePiece(2);
                bottomBack.put(DOWN, RubiksColour.BLUE);
                bottomBack.put(BACK, RubiksColour.RED);
                CubePiece bottomRight = new CubePiece(2);
                bottomRight.put(DOWN, RubiksColour.GREEN);
                bottomRight.put(RIGHT, RubiksColour.ORANGE);
                CubePiece[] bottomEdges = new CubePiece[]{bottomFront, bottomLeft, bottomBack, bottomRight};
                setBottomEdges(bottomEdges);

                //Middle Edges
                CubePiece frontLeft = new CubePiece(2);
                frontLeft.put(FRONT, RubiksColour.YELLOW);
                frontLeft.put(LEFT, RubiksColour.BLUE);
                CubePiece backLeft = new CubePiece(2);
                backLeft.put(BACK, RubiksColour.BLUE);
                backLeft.put(LEFT, RubiksColour.WHITE);
                CubePiece backRight = new CubePiece(2);
                backRight.put(RIGHT, RubiksColour.BLUE);
                backRight.put(BACK, RubiksColour.ORANGE);
                CubePiece frontRight = new CubePiece(2);
                frontRight.put(RIGHT, RubiksColour.RED);
                frontRight.put(FRONT, RubiksColour.YELLOW);
                CubePiece[] middleEdges = new CubePiece[]{frontLeft, backLeft, backRight, frontRight};
                setMiddleEdges(middleEdges);

                CubePiece front = new CubePiece(1);
                front.put(FRONT, RubiksColour.GREEN);
                CubePiece left = new CubePiece(1);
                left.put(LEFT, RubiksColour.YELLOW);
                CubePiece back = new CubePiece(1);
                back.put(BACK, RubiksColour.BLUE);
                CubePiece right = new CubePiece(1);
                right.put(RIGHT, RubiksColour.WHITE);
                CubePiece top = new CubePiece(1);
                top.put(UP, RubiksColour.ORANGE);
                CubePiece bottom = new CubePiece(1);
                bottom.put(DOWN, RubiksColour.RED);
                CubePiece[] centerPieces = new CubePiece[]{front, left, back, right, top, bottom};
                setCenterPieces(centerPieces);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
