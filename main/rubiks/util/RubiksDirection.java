package rubiks.util;

/**
 * Created by Kaan on 2017-07-28.
 */
public enum RubiksDirection {
    UP(0,"U"),
    DOWN(2,"D"),
    LEFT(0,"L"),
    RIGHT(2,"R"),
    BACK(2,"B"),
    FRONT(0,"F");

    private RubiksDirection[] clockwiseSequence;
    private RubiksDirection oppositeDirection;
    private String moveLetter;
    int ordinalPosition;

    static {
        UP.clockwiseSequence = new RubiksDirection[]{LEFT, BACK, RIGHT, FRONT};
        UP.oppositeDirection = DOWN;
        DOWN.clockwiseSequence = new RubiksDirection[]{LEFT,FRONT,RIGHT,BACK};
        DOWN.oppositeDirection = UP;
        LEFT.clockwiseSequence = new RubiksDirection[]{UP,FRONT,DOWN,BACK};
        LEFT.oppositeDirection = RIGHT;
        RIGHT.clockwiseSequence = new RubiksDirection[]{UP,BACK,DOWN,FRONT};
        RIGHT.oppositeDirection = LEFT;
        FRONT.clockwiseSequence = new RubiksDirection[]{RIGHT,DOWN,LEFT,UP};
        FRONT.oppositeDirection = BACK;
        BACK.clockwiseSequence = new RubiksDirection[]{RIGHT,UP,LEFT,DOWN};
        BACK.oppositeDirection = FRONT;
    }

    RubiksDirection(int ordinalPosition, String moveLetter){
        this.ordinalPosition = ordinalPosition;
        this.moveLetter = moveLetter;
    }

    public RubiksDirection getOppositeDirection(){
        return oppositeDirection;
    }

    public RubiksDirection[] getClockwiseSequence(){
        return clockwiseSequence;
    }

    public int getOrdinalPosition() {
        return ordinalPosition;
    }

    public String getMoveLetter(){
        return moveLetter;
    }

    public static RubiksDirection getRubiksDirectionFromRotation(String rotation){
        RubiksDirection rubiksDirection = null;
        if(rotation.contains("U")){
            rubiksDirection = RubiksDirection.UP;
        }else if(rotation.contains("D")){
            rubiksDirection = RubiksDirection.DOWN;
        }else if(rotation.contains("L")){
            rubiksDirection = RubiksDirection.LEFT;
        }else if(rotation.contains("R")){
            rubiksDirection = RubiksDirection.RIGHT;
        }else if(rotation.contains("F")){
            rubiksDirection = RubiksDirection.FRONT;
        }else if(rotation.contains("B")){
            rubiksDirection = RubiksDirection.BACK;
        }
        return rubiksDirection;
    }
}
