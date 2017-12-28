package rubiks;

import rubiks.rubikscube.Cube;
import rubiks.solver.Solver;
import rubiks.solver.RubiksResults;

/**
 * Created by Kaan on 2017-07-29.
 */
public class Experiment {
    public static void main(String[] args){
       try {
           RubiksResults moves = new RubiksResults();
           Cube cube = new TestingCube(1);

           Solver solver = new Solver(cube,moves);
           solver.solveCube();
           return;
       }catch (Exception e){
           e.printStackTrace();
       }


    }
}
