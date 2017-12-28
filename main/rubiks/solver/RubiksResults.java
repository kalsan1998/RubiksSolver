package rubiks.solver;

import rubiks.Rotator;
import rubiks.util.RubiksDirection;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kaan on 2017-08-12.
 */
public class RubiksResults extends LinkedHashMap<String,LinkedList>{

    public boolean addAllMoves(String phase, String ... moves){
        for(String move : moves){
            this.addMove(phase, move);
        }
        return true;
    }

    //TODO: add logger instead of print stack trace

    public boolean addMove(String phase, String move) {
        if(get(phase) == null){
            put(phase, new LinkedList<>());
        }

        LinkedList<String> list = get(phase);
        return addMoveToList(list, move);
    }

    public boolean addMessage(String phase, String message){
        if(get(phase) == null){
            put(phase, new LinkedList<>());
        }
        List list = get(phase);
        return list.add(message);
    }

    private boolean addMoveToList(LinkedList<String> list, String move){
        if(!list.isEmpty()) {
            RubiksDirection side = RubiksDirection.getRubiksDirectionFromRotation(move);
            String lastMove = list.getLast();
            RubiksDirection lastMoveSide = RubiksDirection.getRubiksDirectionFromRotation(lastMove);
            if (lastMoveSide != null) {
                if (Rotator.getOppositeRotation(move).equals(lastMove)) {
                    list.removeLast();
                    return true;
                } else if (side == lastMoveSide) {
                    list.removeLast();
                    return list.add(Rotator.combineMoves(move, lastMove));
                } else if (lastMoveSide.getOppositeDirection() == side) {
                    list.removeLast();
                    addMoveToList(list, move);
                    return list.add(lastMove);
                }
            }
        }
        return list.add(move);
    }
}
