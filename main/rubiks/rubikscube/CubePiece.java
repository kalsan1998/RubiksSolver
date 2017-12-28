package rubiks.rubikscube;

import rubiks.Rotator;
import rubiks.solver.PieceTracer;
import rubiks.util.RubiksColour;
import rubiks.util.RubiksCubeException;
import rubiks.util.RubiksCubeValidator;
import rubiks.util.RubiksDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kaan Alsan on 7/1/2017.
 */
public class CubePiece extends HashMap<RubiksDirection,RubiksColour>{
    public static final int NULL_PIECE = 0;
    public static final int CENTER_PIECE = 1;
    public static final int EDGE_PIECE = 2;
    public static final int CORNER_PIECE = 3;

    private int pieceType;

    public CubePiece(int pieceType){
        this.pieceType = pieceType;
    }

    @Override
    public RubiksColour get(Object key){
        RubiksColour value = super.get(key);
        if(value == null){
            return RubiksColour.NO_COLOUR;
        }else {
            return value;
        }
    }

    public List<RubiksDirection> getPieceLocation(){
        List<RubiksDirection> location = new ArrayList<>();
        for(RubiksDirection direction : this.keySet()){
            if(this.get(direction) != RubiksColour.NO_COLOUR){
                location.add(direction);
            }
        }
        return location;
    }

    public CubePiece movePieceColours(String move){
        RubiksDirection[] sequence = Rotator.getSequenceFromMove(move);
        CubePiece newCubePiece = (CubePiece) clone();
        newCubePiece.put(sequence[0],get(sequence[3]));
        for(int i = 1; i < 4; i++){
            newCubePiece.put(sequence[i], get(sequence[i-1]));
        }
        clear();
        for(RubiksDirection key : newCubePiece.keySet()){
            RubiksColour value = newCubePiece.get(key);
            if(value != null && value!= RubiksColour.NO_COLOUR) {
                put(key,value);
            }
        }
        return this;
    }

    public RubiksColour getRightColour(){
        return this.get(RubiksDirection.RIGHT);
    }
    public RubiksColour getLeftColour(){
        return this.get(RubiksDirection.LEFT);
    }
    public RubiksColour getTopColour(){
        return this.get(RubiksDirection.UP);
    }
    public RubiksColour getBottomColour(){
        return this.get(RubiksDirection.DOWN);
    }
    public RubiksColour getFrontColour(){
        return this.get(RubiksDirection.FRONT);
    }
    public RubiksColour getBackColour(){
        return this.get(RubiksDirection.BACK);
    }

    public int getPieceType(){
        return this.pieceType;
    }

}
