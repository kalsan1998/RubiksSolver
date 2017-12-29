package rubiks.solver;

import org.kie.api.runtime.KieSession;
import rubiks.drools.KieSessionMaker;
import rubiks.rubikscube.Cube;
import rubiks.rubikscube.CubePiece;
import rubiks.util.RubiksColour;
import rubiks.util.RubiksCubeException;
import rubiks.util.RubiksCubeValidator;
import rubiks.util.RubiksDirection;

import java.util.*;

/**
 * Created by Kaan on 2017-07-28.
 */
public class Solver {
    private RubiksColour bottomColour;
    private RubiksColour topColour;
    private KieSession kieSession;
    private boolean isKieSessionInitialized = false;
    private RubiksResults movesExecuted;
    private List<CubePiece> piecesNotInPlace = new ArrayList<>();
    protected Map<RubiksDirection,RubiksColour> directionToColourMap = new HashMap<>();
    protected Map<RubiksColour,RubiksDirection> colourToDirectionMap = new HashMap<>();
    protected Cube cube;

    private static final String getMiddleEdgePieces = "get bottom edge pieces not in place";
    private static final String getCornerPieces = "get bottom corner pieces not in place";
    private static final String getMiddlePieces = "get middle edge pieces not in place";
    private static final String solveBottomEdgePiece = "solve bottom edges";
    private static final String solveCornerPiece = "solve bottom corners";
    private static final String solveMiddleEdgePiece = "solve middle edges";
    private static final String solveTopCross = "solve top cross";
    private static final String solveTopEdges = "solve top edges";

    private long allowedRuntimeMilliseconds;
    private boolean isComplete = false;


    public Solver(Cube cube, RubiksResults movesExecuted, long allowedRuntimeMilliseconds){
        this.cube = cube;
        this.directionToColourMap = cube.getDirectionToColourMap();
        this.colourToDirectionMap = cube.getColourToDirectionMap();
        bottomColour = directionToColourMap.get(RubiksDirection.DOWN);
        topColour = directionToColourMap.get(RubiksDirection.UP);
        this.movesExecuted = movesExecuted;
        this.allowedRuntimeMilliseconds = allowedRuntimeMilliseconds;
    }

    public Cube solveCube(){
        boolean cubeValid = false;
        try{
            cubeValid = RubiksCubeValidator.isCubeValid(cube);
        }catch (RubiksCubeException e){
            movesExecuted.addMessage("error", "Cube not valid: "+ e.getMessage());
        }
        if(cubeValid) {
            Thread solve = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        solvePiecesPhase(getMiddleEdgePieces, solveBottomEdgePiece);
                        System.out.println("-------------------------------\n BOTTOM EDGES SOLVED \n-------------------------------");
                        movesExecuted.addMessage(solveBottomEdgePiece, "COMPLETED BOTTOM EDGES");
                        solvePiecesPhase(getCornerPieces, solveCornerPiece);
                        movesExecuted.addMessage(solveCornerPiece, "COMPLETED BOTTOM CORNERS");
                        System.out.println("-------------------------------\n BOTTOM CORNERS SOLVED \n-------------------------------");
                        solvePiecesPhase(getMiddlePieces, solveMiddleEdgePiece);
                        movesExecuted.addMessage(solveMiddleEdgePiece, "COMPLETED MIDDLE EDGES");
                        System.out.println("-------------------------------\n MIDDLE EDGES SOLVED \n-------------------------------");
                        solveCubePhase(solveTopCross);
                        movesExecuted.addMessage(solveTopCross, "COMPLETED TOP CROSS");
                        System.out.println("-------------------------------\n TOP CROSS SOLVED \n-------------------------------");
                        solveCubePhase(solveTopEdges);
                        movesExecuted.addMessage(solveTopEdges, "COMPLETED TOP EDGES");
                        System.out.println("-------------------------------\n TOP EDGES SOLVED \n-------------------------------");
                    }catch (Exception e) {
                        movesExecuted.addMessage("error", "Invalid cube layout. Make sure colours were inputted correctly.");
                    }
                    isComplete = true;
                }
            });
            solve.start();
            Long startTime = new Date().getTime();
            Long currentTime = new Date().getTime();
            while(!isComplete) {
                if((currentTime - allowedRuntimeMilliseconds) > startTime) {
                    movesExecuted.addMessage("error", "Invalid cube layout. Make sure colours were inputted correctly.");
                    solve.stop();
                    break;
                }
                try{
                    Thread.sleep(500);
                }catch (Exception e) {
                    e.printStackTrace();
                }
                currentTime = new Date().getTime();
            }
        }
        return cube;
    }

    private Cube solveCubePhase(String phase){
        initializeKieSession(phase);
        kieSession.insert(cube);
        kieSession.getAgenda().getAgendaGroup(phase).setFocus();
        kieSession.fireAllRules();
        kieSession.dispose();
        return cube;
    }

    private Cube solvePiecesPhase(String find, String phase){
        piecesNotInPlace = new ArrayList<>();
        if(!isKieSessionInitialized){
            initializeKieSession(phase);
        }
        kieSession.insert(cube);
        kieSession.getAgenda().getAgendaGroup(find).setFocus();
        kieSession.fireAllRules();
        while (!piecesNotInPlace.isEmpty()){
            kieSession.insert(piecesNotInPlace.get(0));
            kieSession.getAgenda().getAgendaGroup(phase).setFocus();
            kieSession.fireAllRules();
            piecesNotInPlace.clear();
            kieSession.insert(cube);
            kieSession.getAgenda().getAgendaGroup(find).setFocus();
            kieSession.fireAllRules();
        }
        kieSession.dispose();
        isKieSessionInitialized = false;
        return cube;
    }

    private void initializeKieSession(String phase){
        kieSession = KieSessionMaker.getKieSession();
        kieSession.setGlobal("baseColour", bottomColour);
        kieSession.setGlobal("topColour", topColour);
        kieSession.setGlobal("directions",RubiksDirection.values());
        kieSession.setGlobal("directionToColourMap", directionToColourMap);
        kieSession.setGlobal("colourToDirectionMap", colourToDirectionMap);
        kieSession.setGlobal("cube",cube);
        kieSession.setGlobal("movesExecuted", movesExecuted);
        kieSession.setGlobal("piecesNotInPlace", piecesNotInPlace);
        kieSession.setGlobal("phase", phase);
        isKieSessionInitialized = true;
    }

    private void insertAllPieces(){
        for(CubePiece[][] cubePieces : cube.getPieces()){
            for (CubePiece[] cubePieces1 : cubePieces){
                for(CubePiece cubePiece : cubePieces1){
                    kieSession.insert(cubePiece);
                }
            }
        }
    }
}
