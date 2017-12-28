package rubiks.solver;

import rubiks.rubikscube.CubePiece;
import rubiks.util.RubiksColour;
import rubiks.util.RubiksDirection;

/**
 * Created by Kaan on 2017-07-28.
 */
public class PieceTracer {
    public int movesToCorrectPosition;
    public String nextMove;
    public CubePiece tracedPiece;

    public PieceTracer(CubePiece cubePiece){
        this.tracedPiece = cubePiece;
    }

//    public PieceTracer clone(){
//        PieceTracer pieceTracerClone = new PieceTracer((CubePiece) tracedPiece.clone());
//        pieceTracerClone.nextMove = this.nextMove;
//        pieceTracerClone.movesToCorrectPosition = this.movesToCorrectPosition;
//        return pieceTracerClone;
//    }
}
