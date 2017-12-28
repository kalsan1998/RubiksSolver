package CubeMoves;

import rubiks.rubikscube.Cube;
import rubiks.rubikscube.CubePiece;
import rubiks.TestingCube;
import rubiks.util.RubiksColour;
import rubiks.util.RubiksCubeException;
import rubiks.util.RubiksCubeValidator;
import junit.framework.TestCase;
import org.junit.Test;
import rubiks.Rotator;

/**
 * Created by Kaan Alsan on 7/1/2017.
 */
public class RotateFaceTest extends TestCase {
    private TestingCube testingCube1 = new TestingCube(1);

    @Test
    public void testLeftFaceRotation() throws Exception{
        Cube cube = Rotator.rotateFace(testingCube1,"L");
        CubePiece[][][] cubePieces = cube.getPieces();
        CubePiece topLeftFront = cubePieces[0][0][0];
        assertEquals(RubiksColour.WHITE,topLeftFront.getFrontColour());
        assertEquals(RubiksColour.BLUE,topLeftFront.getTopColour());
        assertEquals(RubiksColour.ORANGE,topLeftFront.getLeftColour());
        CubePiece leftFront = cubePieces[0][1][0];
        assertEquals(RubiksColour.YELLOW,leftFront.getLeftColour());
        assertEquals(RubiksColour.GREEN,leftFront.getFrontColour());
        CubePiece bottomLeftFront = cubePieces[0][2][0];
        assertEquals(RubiksColour.RED,bottomLeftFront.getFrontColour());
        assertEquals(RubiksColour.WHITE,bottomLeftFront.getLeftColour());
        assertEquals(RubiksColour.GREEN,bottomLeftFront.getBottomColour());
        CubePiece bottomLeft = cubePieces[0][2][1];
        assertEquals(RubiksColour.YELLOW,bottomLeft.getBottomColour());
        assertEquals(RubiksColour.BLUE,bottomLeft.getLeftColour());
        CubePiece bottomLeftBack = cubePieces[0][2][2];
        assertEquals(RubiksColour.YELLOW,bottomLeftBack.getLeftColour());
        assertEquals(RubiksColour.RED,bottomLeftBack.getBottomColour());
        assertEquals(RubiksColour.BLUE,bottomLeftBack.getBackColour());
        CubePiece leftBack = cubePieces[0][1][2];
        assertEquals(RubiksColour.WHITE,leftBack.getLeftColour());
        assertEquals(RubiksColour.RED,leftBack.getBackColour());
        CubePiece topLeftBack = cubePieces[0][0][2];
        assertEquals(RubiksColour.GREEN,topLeftBack.getLeftColour());
        assertEquals(RubiksColour.YELLOW,topLeftBack.getTopColour());
        assertEquals(RubiksColour.RED,topLeftBack.getBackColour());
        CubePiece topLeft = cubePieces[0][0][1];
        assertEquals(RubiksColour.WHITE,topLeft.getLeftColour());
        assertEquals(RubiksColour.BLUE,topLeft.getTopColour());
        for(int a = 1; a < 3; a++) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    assertEquals(cubePieces[a][i][j], testingCube1.getPieces()[a][i][j]);
                }
            }
        }
        assertTrue(testingCube1.equals( Rotator.rotateFace(cube,"L\'")));
        assertTrue(RubiksCubeValidator.isCubeValid(testingCube1));
    }

    @Test
    public void testRightFaceRotation() throws RubiksCubeException{
        Rotator.rotateFace(testingCube1,"R\'");
        CubePiece[][][] cubePieces = testingCube1.getPieces();
        CubePiece topRightFront = cubePieces[2][0][0];
        assertEquals(RubiksColour.WHITE,topRightFront.getFrontColour());
        assertEquals(RubiksColour.BLUE,topRightFront.getTopColour());
        assertEquals(RubiksColour.RED,topRightFront.getRightColour());
        CubePiece rightFront = cubePieces[2][1][0];
        assertEquals(RubiksColour.WHITE,rightFront.getRightColour());
        assertEquals(RubiksColour.GREEN,rightFront.getFrontColour());
        CubePiece bottomRightFront = cubePieces[2][2][0];
        assertEquals(RubiksColour.ORANGE,bottomRightFront.getFrontColour());
        assertEquals(RubiksColour.WHITE,bottomRightFront.getRightColour());
        assertEquals(RubiksColour.GREEN,bottomRightFront.getBottomColour());
        CubePiece bottomRight = cubePieces[2][2][1];
        assertEquals(RubiksColour.YELLOW,bottomRight.getBottomColour());
        assertEquals(RubiksColour.RED,bottomRight.getRightColour());
        CubePiece bottomRightBack = cubePieces[2][2][2];
        assertEquals(RubiksColour.ORANGE,bottomRightBack.getRightColour());
        assertEquals(RubiksColour.YELLOW,bottomRightBack.getBottomColour());
        assertEquals(RubiksColour.GREEN,bottomRightBack.getBackColour());
        CubePiece rightBack = cubePieces[2][1][2];
        assertEquals(RubiksColour.ORANGE,rightBack.getRightColour());
        assertEquals(RubiksColour.GREEN,rightBack.getBackColour());
        CubePiece topRightBack = cubePieces[2][0][2];
        assertEquals(RubiksColour.BLUE,topRightBack.getRightColour());
        assertEquals(RubiksColour.ORANGE,topRightBack.getTopColour());
        assertEquals(RubiksColour.YELLOW,topRightBack.getBackColour());
        CubePiece topRight = cubePieces[2][0][1];
        assertEquals(RubiksColour.BLUE,topRight.getRightColour());
        assertEquals(RubiksColour.ORANGE,topRight.getTopColour());
    }

    @Test
    public void testTopFaceRotation() throws RubiksCubeException{
        Cube cube = Rotator.rotateFace(testingCube1,"U");
        CubePiece[][][] cubePieces = cube.getPieces();
        CubePiece topLeftFront = cubePieces[0][0][0];
        assertEquals(RubiksColour.ORANGE,topLeftFront.getTopColour());
        assertEquals(RubiksColour.GREEN,topLeftFront.getLeftColour());
        assertEquals(RubiksColour.WHITE,topLeftFront.getFrontColour());
        CubePiece topLeft = cubePieces[0][0][1];
        assertEquals(RubiksColour.ORANGE,topLeft.getTopColour());
        assertEquals(RubiksColour.YELLOW,topLeft.getLeftColour());
        CubePiece topLeftBack = cubePieces[0][0][2];
        assertEquals(RubiksColour.GREEN,topLeftBack.getLeftColour());
        assertEquals(RubiksColour.WHITE,topLeftBack.getBackColour());
        assertEquals(RubiksColour.RED,topLeftBack.getTopColour());
        CubePiece topBack = cubePieces[1][0][2];
        assertEquals(RubiksColour.GREEN,topBack.getTopColour());
        assertEquals(RubiksColour.YELLOW,topBack.getBackColour());
        CubePiece topRightBack = cubePieces[2][0][2];
        assertEquals(RubiksColour.WHITE,topRightBack.getTopColour());
        assertEquals(RubiksColour.BLUE,topRightBack.getRightColour());
        assertEquals(RubiksColour.ORANGE,topRightBack.getBackColour());
        CubePiece topRight = cubePieces[2][0][1];
        assertEquals(RubiksColour.GREEN,topRight.getTopColour());
        assertEquals(RubiksColour.RED,topRight.getRightColour());
        CubePiece topRightFront = cubePieces[2][0][0];
        assertEquals(RubiksColour.WHITE,topRightFront.getTopColour());
        assertEquals(RubiksColour.BLUE,topRightFront.getRightColour());
        assertEquals(RubiksColour.RED,topRightFront.getFrontColour());
        CubePiece topFront = cubePieces[1][0][0];
        assertEquals(RubiksColour.GREEN,topFront.getTopColour());
        assertEquals(RubiksColour.WHITE,topFront.getFrontColour());

        for(int a = 0; a < 3; a++) {
            for (int i = 1; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    assertEquals(cubePieces[a][i][j], testingCube1.getPieces()[a][i][j]);
                }
            }
        }
        assertTrue(testingCube1.equals( Rotator.rotateFace(cube,"U\'")));
    }

    @Test
    public void testRotateBottomFace() throws RubiksCubeException{
        Cube cube = Rotator.rotateFace(testingCube1,"D");
        CubePiece[][][] cubePieces = cube.getPieces();
        CubePiece bottomLeftFront = cubePieces[0][2][0];
        assertEquals(RubiksColour.RED,bottomLeftFront.getBottomColour());
        assertEquals(RubiksColour.YELLOW,bottomLeftFront.getLeftColour());
        assertEquals(RubiksColour.GREEN,bottomLeftFront.getFrontColour());
        CubePiece bottomLeft = cubePieces[0][2][1];
        assertEquals(RubiksColour.BLUE,bottomLeft.getBottomColour());
        assertEquals(RubiksColour.RED,bottomLeft.getLeftColour());
        CubePiece bottomLeftBack = cubePieces[0][2][2];
        assertEquals(RubiksColour.YELLOW,bottomLeftBack.getBottomColour());
        assertEquals(RubiksColour.ORANGE,bottomLeftBack.getLeftColour());
        assertEquals(RubiksColour.BLUE,bottomLeftBack.getBackColour());
        CubePiece bottomBack = cubePieces[1][2][2];
        assertEquals(RubiksColour.GREEN,bottomBack.getBottomColour());
        assertEquals(RubiksColour.ORANGE,bottomBack.getBackColour());
        CubePiece bottomRightBack = cubePieces[2][2][2];
        assertEquals(RubiksColour.GREEN,bottomRightBack.getBottomColour());
        assertEquals(RubiksColour.YELLOW,bottomRightBack.getRightColour());
        assertEquals(RubiksColour.ORANGE,bottomRightBack.getBackColour());
        CubePiece bottomRight = cubePieces[2][2][1];
        assertEquals(RubiksColour.WHITE,bottomRight.getBottomColour());
        assertEquals(RubiksColour.ORANGE,bottomRight.getRightColour());
        CubePiece bottomRightFront = cubePieces[2][2][0];
        assertEquals(RubiksColour.BLUE,bottomRightFront.getBottomColour());
        assertEquals(RubiksColour.RED,bottomRightFront.getRightColour());
        assertEquals(RubiksColour.YELLOW,bottomRightFront.getFrontColour());
        CubePiece bottomFront = cubePieces[1][2][0];
        assertEquals(RubiksColour.RED,bottomFront.getBottomColour());
        assertEquals(RubiksColour.WHITE,bottomFront.getFrontColour());

        for(int a = 0; a < 3; a++) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    assertEquals(cubePieces[a][i][j], testingCube1.getPieces()[a][i][j]);
                }
            }
        }
        assertTrue(testingCube1.equals( Rotator.rotateFace(cube,"D\'")));
    }

    @Test
    public void testFrontBackRotations() throws RubiksCubeException{
        assertTrue(testingCube1.equals(Rotator.rotateFace(Rotator.rotateFace(Rotator.rotateFace(Rotator.rotateFace(testingCube1,"F"),"F"),"F"),"F")));
        assertTrue(testingCube1.equals(Rotator.rotateFace(Rotator.rotateFace(Rotator.rotateFace(Rotator.rotateFace(testingCube1,"F\'"),"F\'"),"F\'"),"F\'")));
        assertTrue(testingCube1.equals(Rotator.rotateFace(Rotator.rotateFace(Rotator.rotateFace(Rotator.rotateFace(testingCube1,"B"),"B"),"B"),"B")));
        assertTrue(testingCube1.equals(Rotator.rotateFace(Rotator.rotateFace(Rotator.rotateFace(Rotator.rotateFace(testingCube1,"B\'"),"B\'"),"B\'"),"B\'")));


    }
}
