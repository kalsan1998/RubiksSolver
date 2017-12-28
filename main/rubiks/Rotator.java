package rubiks;

import rubiks.rubikscube.Cube;
import rubiks.rubikscube.CubePiece;
import rubiks.util.RubiksCubeException;
import rubiks.util.RubiksDirection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * Created by Kaan Alsan on 7/1/2017.
 */
public abstract class Rotator {
    public static Cube rotateFace(Cube oldCube, String ... rotations) throws RubiksCubeException {
        for(String rotation : rotations) {
            if(rotation.equals("")){
                return oldCube;
            }
            else if (!rotation.endsWith("2")) {
                oldCube = doRotateFace(oldCube, rotation);
            } else {
                String quarterRotation = rotation.substring(0, 1);
                oldCube = doRotateFace(doRotateFace(oldCube, quarterRotation), quarterRotation);
            }
        }
        return oldCube;
    }
    public static Cube doRotateFace(Cube oldCube, String rotation) throws RubiksCubeException {
        CubePiece[][][] oldCubePieces = oldCube.getPieces();
        CubePiece[][][] newCubePieces = oldCube.createDeepCopy().getPieces();
        RubiksDirection rubiksDirection = RubiksDirection.getRubiksDirectionFromRotation(rotation);
        int side = rubiksDirection.getOrdinalPosition();
        int oldInversePositionValue = 0;
        int newInversePositionValue;
        while(oldInversePositionValue < oldCube.getPieces().length){
            if(oldInversePositionValue == 0){
                 newInversePositionValue = 2;
            }else if(oldInversePositionValue == 1){
                newInversePositionValue = 1;
            }else if (oldInversePositionValue == 2){
                newInversePositionValue = 0;
            }else{
                newInversePositionValue = -1;
            }
            int nonChangingPositionValue = 0;
            while (nonChangingPositionValue < 3){
                if(rotation.equalsIgnoreCase("L") || rotation.equalsIgnoreCase("R\'")) {
                    //Depth becomes height, height becomes depth inverse
                    newCubePieces[side][newInversePositionValue][nonChangingPositionValue] = oldCubePieces[side][nonChangingPositionValue][oldInversePositionValue].movePieceColours(rotation);
                }else if(rotation.equalsIgnoreCase("L\'") || rotation.equalsIgnoreCase("R")) {
                    //Depth becomes height inverse, height become depth
                    newCubePieces[side][nonChangingPositionValue][newInversePositionValue] = oldCubePieces[side][oldInversePositionValue][nonChangingPositionValue].movePieceColours(rotation);
                }else if(rotation.equalsIgnoreCase("D") || rotation.equalsIgnoreCase("U\'")) {
                    //Depth becomes width inverse, width becomes depth
                    newCubePieces[newInversePositionValue][side][nonChangingPositionValue] = oldCubePieces[nonChangingPositionValue][side][oldInversePositionValue].movePieceColours(rotation);
                }else if(rotation.equalsIgnoreCase("D\'") || rotation.equalsIgnoreCase("U")) {
                    //Depth becomes width, width becomes depth inverse
                    newCubePieces[nonChangingPositionValue][side][newInversePositionValue] = oldCubePieces[oldInversePositionValue][side][nonChangingPositionValue].movePieceColours(rotation);
                }else if(rotation.equalsIgnoreCase("F\'") || rotation.equalsIgnoreCase("B")) {
                    //Height becomes width inverse, width becomes height
                    newCubePieces[nonChangingPositionValue][newInversePositionValue][side] = oldCubePieces[oldInversePositionValue][nonChangingPositionValue][side].movePieceColours(rotation);
                }else if(rotation.equalsIgnoreCase("F") || rotation.equalsIgnoreCase("B\'")) {
                    //Height becomes width, width becomes height inverse
                    newCubePieces[newInversePositionValue][nonChangingPositionValue][side] = oldCubePieces[nonChangingPositionValue][oldInversePositionValue][side].movePieceColours(rotation);
                }else{
                    throw new RubiksCubeException("Invalid direction type");
                    }
                nonChangingPositionValue++;
            }
            oldInversePositionValue ++;
        }

        oldCube.setPieces(newCubePieces);
        return oldCube;
    }

    public static RubiksDirection[] getSequenceFromMove(String rotation){
        RubiksDirection rubiksDirection = RubiksDirection.getRubiksDirectionFromRotation(rotation);
        RubiksDirection[] sequence = rubiksDirection.getClockwiseSequence().clone();
        if(rotation.contains("\'")) Collections.reverse(Arrays.asList(sequence));
        return sequence;

    }


    public static String getRotationNeeded(RubiksDirection rotationSide, RubiksDirection origin, RubiksDirection destination) throws RubiksCubeException{
        int rotationAmount = getRotationAmountNeeded(rotationSide.getClockwiseSequence(), origin, destination);
        return rotationAmount == 0? "" : rotationSide.getMoveLetter() + getRotationType(rotationAmount);
    }

    private static int getRotationAmountNeeded(RubiksDirection[] sequence, RubiksDirection origin, RubiksDirection destination) throws RubiksCubeException{
        int sequenceLength = sequence.length;
        for(int i = 0 ; i < sequenceLength; i++){
            if(sequence[i] == origin){
                for(int j = 0; j < sequenceLength; j++){
                    if(sequence[((i+j) % sequenceLength)] == destination){
                        return j;
                    }
                }
            }
        }
        throw new RubiksCubeException("Unable to get rotations needed - Sequence: " + Arrays.toString(sequence) + ", Origin: " + origin + ", Destination: "+ destination);
    }

    private static String getRotationType(int rotations){
        if (rotations == 2){
            return "2";
        }
        if (rotations == 3){
            return "\'";
        }
        return "";
    }

    public static String combineMoves(String moveOne, String moveTwo) {
        String moveSideString = moveOne.substring(0,1);
        int sumOfRotations = 0;
        if(moveSideString.equalsIgnoreCase(moveTwo.substring(0,1))){
            if(moveOne.endsWith("2") && moveTwo.endsWith("2")){
                return "";
            }
            String[] movesArray = new String[]{moveOne,moveTwo};
            for(String move: movesArray){
                if(move.endsWith("2")){
                    sumOfRotations += 2;
                }else if(move.endsWith("\'")){
                    sumOfRotations += -1;
                }else{
                    sumOfRotations ++;
                }
            }
            return moveSideString + getRotationType(sumOfRotations);
        }else{
            return "Cannot combine";
        }
    }

    public static String getOppositeRotation(String move){
        if(move != null) {
            if (move.equals("")) return move;
            String side = move.substring(0, 1);
            if (move.length() == 1) {
                return side + "\'";
            } else if (move.substring(1).equalsIgnoreCase("\'")) {
                return side;
            } else {
                return side + "2";
            }
        }else return "Not a valid move";
    }
}

