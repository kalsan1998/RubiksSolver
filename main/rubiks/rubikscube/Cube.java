package rubiks.rubikscube;

import rubiks.util.RubiksColour;
import rubiks.util.RubiksDirection;
import rubiks.util.RubiksCubeException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kaan Alsan on 7/1/2017.
 */
public class Cube {
    private CubePiece[][][] pieces;

    private Map<RubiksDirection,RubiksColour> directionToColourMap = new HashMap<>();
    private Map<RubiksColour,RubiksDirection> colourToDirectionMap = new HashMap<>();


    public Cube(){
        this.pieces = new CubePiece[3][3][3];
        pieces[1][1][1] = new CubePiece(CubePiece.NULL_PIECE);
    }

    public Cube(CubePiece[][][] pieces) throws RubiksCubeException{
        this.pieces = pieces;
        directionToColourMap.put(RubiksDirection.UP,pieces[1][0][1].getTopColour());
        directionToColourMap.put(RubiksDirection.DOWN,pieces[1][2][1].getBottomColour());
        directionToColourMap.put(RubiksDirection.FRONT,pieces[1][1][0].getFrontColour());
        directionToColourMap.put(RubiksDirection.BACK,pieces[1][1][2].getBackColour());
        directionToColourMap.put(RubiksDirection.LEFT,pieces[0][1][1].getLeftColour());
        directionToColourMap.put(RubiksDirection.RIGHT,pieces[2][1][1].getRightColour());
        for(RubiksDirection direction : directionToColourMap.keySet()){
            colourToDirectionMap.put(directionToColourMap.get(direction),direction);
        }

        for(RubiksColour value : directionToColourMap.values()){
            if(value == null){
                throw new RubiksCubeException("Center pieces do not have correct colour orientations");
            }
        }
    }

    public List<CubePiece> getAllPiecesList(){
        List<CubePiece> piecesList = new ArrayList<>();
        for (CubePiece[][] cubePieces : pieces){
            for(CubePiece[] cubePieces1 : cubePieces){
                for (CubePiece cubePiece : cubePieces1){
                    piecesList.add(cubePiece);
                }
            }
        }
        return piecesList;
    }

    public Cube createDeepCopy(){
        int length = this.pieces.length;
        CubePiece[][][] newCubePieces = new CubePiece[3][3][3];
        for(int width = 0; width<length; width++){
            for(int height = 0; height<length; height++){
                for(int depth = 0; depth<length;depth++){
                   newCubePieces[width][height][depth] =  (CubePiece) this.pieces[width][height][depth].clone();
                }
            }
        }
        try{
            return new Cube(newCubePieces);
        }catch (RubiksCubeException e){
            e.printStackTrace();
        }
        return new Cube();
    }

    public boolean equals(Cube cube){
        int length = this.pieces.length;
        for(int width = 0; width<length; width++) {
            for (int height = 0; height < length; height++) {
                for (int depth = 0; depth < length; depth++) {
                    if(!this.pieces[width][height][depth].equals(cube.getPieces()[width][height][depth])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public Map<RubiksDirection, RubiksColour> getDirectionToColourMap() {
        return directionToColourMap;
    }
    public Map<RubiksColour,RubiksDirection> getColourToDirectionMap() {
        return colourToDirectionMap;
    }

    public CubePiece[][][] getPieces() {
        return pieces;
    }

    public void setPieces(CubePiece[][][] pieces) {
        this.pieces = pieces;
    }

    public void setTopLeftFront(CubePiece cubePiece) throws RubiksCubeException {
        if(cubePiece.getPieceType() != CubePiece.CORNER_PIECE){
            throw new RubiksCubeException(RubiksCubeException.CORNER_PIECE_MESSAGE);
        }else{
            pieces[0][0][0] = cubePiece;
        }
    }
    public void setTopLeftBack(CubePiece cubePiece) throws RubiksCubeException {
        if(cubePiece.getPieceType() != CubePiece.CORNER_PIECE){
            throw new RubiksCubeException(RubiksCubeException.CORNER_PIECE_MESSAGE);
        }else{
            pieces[0][0][2] = cubePiece;
        }
    }
    public void setTopRightBack(CubePiece cubePiece) throws RubiksCubeException {
        if(cubePiece.getPieceType() != CubePiece.CORNER_PIECE){
            throw new RubiksCubeException(RubiksCubeException.CORNER_PIECE_MESSAGE);
        }else{
            pieces[2][0][2] = cubePiece;
        }
    }
    public void setTopRightFront(CubePiece cubePiece) throws RubiksCubeException {
        if(cubePiece.getPieceType() != CubePiece.CORNER_PIECE){
            throw new RubiksCubeException(RubiksCubeException.CORNER_PIECE_MESSAGE);
        }else{
            pieces[2][0][0] = cubePiece;
        }
    }
    public void setBottomRightFront(CubePiece cubePiece) throws RubiksCubeException {
        if(cubePiece.getPieceType() != CubePiece.CORNER_PIECE){
            throw new RubiksCubeException(RubiksCubeException.CORNER_PIECE_MESSAGE);
        }else{
            pieces[2][2][0] = cubePiece;
        }
    }
    public void setBottomLeftFront(CubePiece cubePiece) throws RubiksCubeException {
        if(cubePiece.getPieceType() != CubePiece.CORNER_PIECE){
            throw new RubiksCubeException(RubiksCubeException.CORNER_PIECE_MESSAGE);
        }else{
            pieces[0][2][0] = cubePiece;
        }
    }
    public void setBottomRightBack(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.CORNER_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.CORNER_PIECE_MESSAGE);
        } else {
            pieces[2][2][2] = cubePiece;
        }
    }
    public void setBottomLeftBack(CubePiece cubePiece) throws RubiksCubeException {
        if(cubePiece.getPieceType() != CubePiece.CORNER_PIECE){
            throw new RubiksCubeException(RubiksCubeException.CORNER_PIECE_MESSAGE);
        }else{
            pieces[0][2][2] = cubePiece;
        }
    }
    public void setTopFront(CubePiece cubePiece) throws RubiksCubeException {
        if(cubePiece.getPieceType() != CubePiece.EDGE_PIECE){
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        }else{
            pieces[1][0][0] = cubePiece;
        }
    }
    public void setTopLeft(CubePiece cubePiece) throws RubiksCubeException {
        if(cubePiece.getPieceType() != CubePiece.EDGE_PIECE){
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        }else{
            pieces[0][0][1] = cubePiece;
        }
    }
    public void setTopBack(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[1][0][2] = cubePiece;
        }
    }
    public void setTopRight(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[2][0][1] = cubePiece;
        }
    }
    public void setBottomFront(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[1][2][0] = cubePiece;
        }
    }
    public void setBottomLeft(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[0][2][1] = cubePiece;
        }
    }
    public void setBottomBack(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[1][2][2] = cubePiece;
        }
    }
    public void setBottomRight(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[2][2][1] = cubePiece;
        }
    }
    public void setLeftFront(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[0][1][0] = cubePiece;
        }
    }
    public void setLeftBack(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[0][1][2] = cubePiece;
        }
    }
    public void setRightBack(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[2][1][2] = cubePiece;
        }
    }
    public void setRightFront(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.EDGE_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.EDGE_PIECE_MESSAGE);
        } else {
            pieces[2][1][0] = cubePiece;
        }
    }
    public void setFront(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.CENTER_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.CENTER_PIECE_MESSAGE);
        } else {
            RubiksColour frontColour = cubePiece.getFrontColour();
            directionToColourMap.put(RubiksDirection.FRONT,frontColour);
            colourToDirectionMap.put(frontColour,RubiksDirection.FRONT);
            pieces[1][1][0] = cubePiece;
        }
    }
    public void setLeft(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.CENTER_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.CENTER_PIECE_MESSAGE);
        } else {
            RubiksColour leftColour = cubePiece.getLeftColour();
            directionToColourMap.put(RubiksDirection.LEFT,leftColour);
            colourToDirectionMap.put(leftColour,RubiksDirection.LEFT);
            pieces[0][1][1] = cubePiece;
        }
    }
    public void setBack(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.CENTER_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.CENTER_PIECE_MESSAGE);
        } else {
            RubiksColour backColour = cubePiece.getBackColour();
            directionToColourMap.put(RubiksDirection.BACK,backColour);
            colourToDirectionMap.put(backColour,RubiksDirection.BACK);
            pieces[1][1][2] = cubePiece;
        }
    }
    public void setRight(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.CENTER_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.CENTER_PIECE_MESSAGE);
        } else {
            RubiksColour rightColour = cubePiece.getRightColour();
            directionToColourMap.put(RubiksDirection.RIGHT,rightColour);
            colourToDirectionMap.put(rightColour,RubiksDirection.RIGHT);
            pieces[2][1][1] = cubePiece;
        }
    }
    public void setTop(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.CENTER_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.CENTER_PIECE_MESSAGE);
        } else {
            RubiksColour topColour = cubePiece.getTopColour();
            directionToColourMap.put(RubiksDirection.UP,topColour);
            colourToDirectionMap.put(topColour,RubiksDirection.UP);
            pieces[1][0][1] = cubePiece;
        }
    }
    public void setBottom(CubePiece cubePiece) throws RubiksCubeException {
        if (cubePiece.getPieceType() != CubePiece.CENTER_PIECE) {
            throw new RubiksCubeException(RubiksCubeException.CENTER_PIECE_MESSAGE);
        } else {
            RubiksColour bottomColour = cubePiece.getBottomColour();
            directionToColourMap.put(RubiksDirection.DOWN,bottomColour);
            colourToDirectionMap.put(bottomColour,RubiksDirection.DOWN);
            pieces[1][2][1] = cubePiece;
        }
    }




    /**
     * Ordered clockwise from fronttopleft
     * @param cubePieces
     */
    public void setTopCorners(CubePiece[] cubePieces) throws RubiksCubeException {
        setTopLeftFront(cubePieces[0]);
        setTopLeftBack(cubePieces[1]);
        setTopRightBack(cubePieces[2]);
        setTopRightFront(cubePieces[3]);
    }

    /**
     * Ordered clockwise from bottomfrontleft
     */
    public void setBottomCorners(CubePiece[] cubePieces) throws RubiksCubeException {
        setBottomLeftFront(cubePieces[0]);
        setBottomLeftBack(cubePieces[1]);
        setBottomRightBack(cubePieces[2]);
        setBottomRightFront(cubePieces[3]);
    }

    /**
     * Ordered clockwise from top front edge
     * @param cubePieces
     */
    public void setTopEdges(CubePiece [] cubePieces) throws RubiksCubeException {
        setTopFront(cubePieces[0]);
        setTopLeft(cubePieces[1]);
        setTopBack(cubePieces[2]);
        setTopRight(cubePieces[3]);
    }

    /**
     * Ordered clockwise from bottom front edge
     * @param cubePieces
     */
    public void setBottomEdges(CubePiece[] cubePieces) throws RubiksCubeException {
        setBottomFront(cubePieces[0]);
        setBottomLeft(cubePieces[1]);
        setBottomBack(cubePieces[2]);
        setBottomRight(cubePieces[3]);
    }

    /**
     * Ordered clockwise from middle front left edge
     * @param cubePieces
     */
    public void setMiddleEdges(CubePiece[] cubePieces) throws RubiksCubeException {
        setLeftFront(cubePieces[0]);
        setLeftBack(cubePieces[1]);
        setRightBack(cubePieces[2]);
        setRightFront(cubePieces[3]);
    }

    /**
     * Ordered clcokwise from front center piece followed by top and bottom center piece
     * @param cubePieces
     */
    public void setCenterPieces(CubePiece[] cubePieces) throws RubiksCubeException {
        pieces[1][1][1] = new CubePiece(0);
        setFront(cubePieces[0]);
        setLeft(cubePieces[1]);
        setBack(cubePieces[2]);
        setRight(cubePieces[3]);
        setTop(cubePieces[4]);
        setBottom(cubePieces[5]);
    }
}
