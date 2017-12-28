package rubiks.util;

/**
 * Created by Kaan Alsan on 7/1/2017.
 */
public class RubiksCubeException extends Exception {
    public static final String CORNER_PIECE_MESSAGE = "Cube piece must be a corner piece";
    public static final String EDGE_PIECE_MESSAGE = "Cube piece must be an edge piece";
    public static final String CENTER_PIECE_MESSAGE = "Cube piece must be a center piece";

    public RubiksCubeException(String message){
        super(message);
    }
}
