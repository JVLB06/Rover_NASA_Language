package nasa.rover;
import nasa.rover.Consts;

public class Consts {
    public static final int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
    };

    public static final int walkLimit = matrix[0].length / 4;
    public static final int walkMinimum = 1;

    public static final String rules = "";

    public static final String openPhrase = "Hello World";
    public static final String matrixPath = "O";
    public static final String matrixObstacle = "X";
    public static final String matrixVisited = "*";
    public static final String matrixRover = "R";
}
