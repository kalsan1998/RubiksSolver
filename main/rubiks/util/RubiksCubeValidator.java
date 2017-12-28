package rubiks.util;

import rubiks.rubikscube.Cube;
import rubiks.rubikscube.CubePiece;

import java.util.*;

/**
 * Created by Kaan on 2017-07-19.
 */
public abstract class RubiksCubeValidator {
    private static final int EDGE_COLOUR_COUNT = 4;
    private static final int CORNER_COLOUR_COUNT = 4;
    private static final int CENTER_COLOUR_COUNT = 1;


    public static boolean isCubeValid(Cube cube) throws RubiksCubeException{
        CubePiece[][][] pieces = cube.getPieces();
        List<RubiksColour> cornerPieceColours = new ArrayList<>();
        List<RubiksColour> edgePieceColours = new ArrayList<>();
        List<RubiksColour> centerPieceColours = new ArrayList<>();
        for(CubePiece[][] pieces1 : pieces){
            for (CubePiece[] pieces2 : pieces1 ){
                for (CubePiece cubePiece : pieces2){
                    HashSet<RubiksColour> colors = new HashSet<>(cubePiece.values());
                    if(colors.size() < cubePiece.getPieceType()) {
                        throw new RubiksCubeException("A piece contains two of the same colour");
                    }
                    for(RubiksColour colour : colors){
                        if(colour != null) {
                            int pieceType = cubePiece.getPieceType();
                            if(pieceType == CubePiece.CENTER_PIECE){
                                centerPieceColours.add(colour);
                            }else if(pieceType == CubePiece.EDGE_PIECE){
                                edgePieceColours.add(colour);
                            }else if(pieceType == CubePiece.CORNER_PIECE){
                                cornerPieceColours.add(colour);
                            }else if(pieceType != CubePiece.NULL_PIECE){
                                throw new RubiksCubeException("Cube contains invalid piece type");
                            }
                            if (colors.contains(colour.getOppositeColour())) {
                                throw new RubiksCubeException("Opposite colours cannot be on the same piece");
                            }
                        }
                    }
                }
            }
        }
        return colourCountEquals(centerPieceColours, CENTER_COLOUR_COUNT, "center") &&
                colourCountEquals(edgePieceColours,EDGE_COLOUR_COUNT, "edge") &&
                colourCountEquals(cornerPieceColours,CORNER_COLOUR_COUNT, "corner");
    }

    private static boolean colourCountEquals(List<RubiksColour> colours, int desiredCountPerColour, String type) throws RubiksCubeException{
        for(RubiksColour colour : RubiksColour.values()) {
            if (colour != RubiksColour.NO_COLOUR && Collections.frequency(colours, colour) != desiredCountPerColour) {
                throw new RubiksCubeException("The inputted cube is not a valid. Check that the amount of " + colour + " " + type + " pieces is equal to "+ desiredCountPerColour);
            }
        }
        return true;
    }
}
