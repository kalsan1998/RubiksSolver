package rubiks.util;

/**
 * Created by Kaan on 2017-07-19.
 */
public enum RubiksColour {
    RED, BLUE, WHITE, ORANGE, GREEN, YELLOW, NO_COLOUR;

    public RubiksColour getOppositeColour(){
        if(this == RED){
            return ORANGE;
        }
        if(this == BLUE){
            return GREEN;
        }
        if(this == WHITE){
            return YELLOW;
        }
        if(this == ORANGE){
            return RED;
        }
        if(this == GREEN){
            return BLUE;
        }
        if(this == YELLOW){
            return WHITE;
        }
        return null;
    }
}
