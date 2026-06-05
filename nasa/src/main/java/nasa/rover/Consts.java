package nasa.rover;
import nasa.rover.Consts;

public class Consts {
    public static final int[][] matrix = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 0, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 1, 1, 1, 0},
    };

    public static final int walkLimit = matrix[0].length / 2;
    public static final int walkMinimum = 1;

    public static final String rules = "";

    public static final String forwardCommand = "F";
    public static final char forwardChar = 'F';
    public static final String leftCommand = "L";
    public static final char leftChar = 'L';
    public static final String rightCommand = "R";
    public static final char rightChar = 'R';
    public static final String turnOverCommand = "T";
    public static final char turnOverChar = 'T';

    public static final String openPhrase = "Hi rover. This is your board:";
    public static final String matrixPath = "O";
    public static final String matrixObstacle = "X";
    public static final String matrixVisited = "*";
    public static final String matrixRover = "R";
}
