package CubeMoves;

import rubiks.rubikscube.CubePiece;
import rubiks.Rotator;
import junit.framework.TestCase;
import org.junit.Test;
import rubiks.util.RubiksCubeException;

/**
 * Created by Kaan Alsan on 7/1/2017.
 */
public class MovePieceTest extends TestCase{

    @Test
    public void testEdgePieceOrientationChanges() {
        CubePiece edgePiece = new CubePiece(2);
        edgePiece.put(CubePiece.DOWN, "blue");
        edgePiece.put(CubePiece.RIGHT, "red");

        try {
            edgePiece = Rotator.movePieceColours(edgePiece, "WIDTH UP");
        } catch (RubiksCubeException exception) {
            exception.printStackTrace();
        }

        assertEquals(null, edgePiece.getTopColour());
        assertEquals("blue", edgePiece.getFrontColour());
        assertEquals(null, edgePiece.getBackColour());
        assertEquals(null, edgePiece.getBottomColour());
        assertEquals("red", edgePiece.getRightColour());
        assertEquals(null, edgePiece.getLeftColour());

        try {
            edgePiece = Rotator.movePieceColours(edgePiece, "DEPTH RIGHT");
        } catch (RubiksCubeException exception) {
            exception.printStackTrace();
        }

        assertEquals(null, edgePiece.getTopColour());
        assertEquals("blue", edgePiece.getFrontColour());
        assertEquals(null, edgePiece.getBackColour());
        assertEquals("red", edgePiece.getBottomColour());
        assertEquals(null, edgePiece.getRightColour());
        assertEquals(null, edgePiece.getLeftColour());

        try {
            edgePiece = Rotator.movePieceColours(edgePiece, "HEIGHT LEFT");
        } catch (RubiksCubeException exception) {
            exception.printStackTrace();
        }

        assertEquals(null, edgePiece.getTopColour());
        assertEquals(null, edgePiece.getFrontColour());
        assertEquals(null, edgePiece.getBackColour());
        assertEquals("red", edgePiece.getBottomColour());
        assertEquals("blue", edgePiece.getRightColour());
        assertEquals(null, edgePiece.getLeftColour());

        try {
            edgePiece = Rotator.movePieceColours(Rotator.movePieceColours(edgePiece, "HEIGHT RIGHT"), "HEIGHT RIGHT");
        } catch (RubiksCubeException exception) {
            exception.printStackTrace();
        }

        assertEquals(null, edgePiece.getTopColour());
        assertEquals(null, edgePiece.getFrontColour());
        assertEquals(null, edgePiece.getBackColour());
        assertEquals("red", edgePiece.getBottomColour());
        assertEquals(null, edgePiece.getRightColour());
        assertEquals("blue", edgePiece.getLeftColour());

        try {
            edgePiece = Rotator.movePieceColours(edgePiece, "WIDTH DOWN");
        } catch (RubiksCubeException exception) {
            exception.printStackTrace();
        }

        assertEquals(null, edgePiece.getTopColour());
        assertEquals(null, edgePiece.getFrontColour());
        assertEquals("red", edgePiece.getBackColour());
        assertEquals(null, edgePiece.getBottomColour());
        assertEquals(null, edgePiece.getRightColour());
        assertEquals("blue", edgePiece.getLeftColour());

        try {
            edgePiece = Rotator.movePieceColours(edgePiece, "DEPTH LEFT");
        } catch (RubiksCubeException exception) {
            exception.printStackTrace();
        }

        assertEquals(null, edgePiece.getTopColour());
        assertEquals(null, edgePiece.getFrontColour());
        assertEquals("red", edgePiece.getBackColour());
        assertEquals("blue", edgePiece.getBottomColour());
        assertEquals(null, edgePiece.getRightColour());
        assertEquals(null, edgePiece.getLeftColour());
    }

    @Test
    public void testCornerPieceOrientationChanges() {
        CubePiece edgePiece = new CubePiece(3);
        edgePiece.put(CubePiece.UP, "blue");
        edgePiece.put(CubePiece.LEFT, "red");
        edgePiece.put(CubePiece.FRONT, "green");

        try {
            edgePiece = Rotator.movePieceColours(edgePiece, "WIDTH UP");
        } catch (RubiksCubeException exception) {
            exception.printStackTrace();
        }

        assertEquals("green", edgePiece.getTopColour());
        assertEquals(null, edgePiece.getFrontColour());
        assertEquals("blue", edgePiece.getBackColour());
        assertEquals(null, edgePiece.getBottomColour());
        assertEquals(null, edgePiece.getRightColour());
        assertEquals("red", edgePiece.getLeftColour());

        try {
            edgePiece = Rotator.movePieceColours(edgePiece, "DEPTH RIGHT");
        } catch (RubiksCubeException exception) {
            exception.printStackTrace();
        }

        assertEquals("red", edgePiece.getTopColour());
        assertEquals(null, edgePiece.getFrontColour());
        assertEquals("blue", edgePiece.getBackColour());
        assertEquals(null, edgePiece.getBottomColour());
        assertEquals("green", edgePiece.getRightColour());
        assertEquals(null, edgePiece.getLeftColour());

        try {
            edgePiece = Rotator.movePieceColours(edgePiece, "HEIGHT LEFT");
        } catch (RubiksCubeException exception) {
            exception.printStackTrace();
        }

        assertEquals("red", edgePiece.getTopColour());
        assertEquals(null, edgePiece.getFrontColour());
        assertEquals("green", edgePiece.getBackColour());
        assertEquals(null, edgePiece.getBottomColour());
        assertEquals(null, edgePiece.getRightColour());
        assertEquals("blue", edgePiece.getLeftColour());
    }
}
