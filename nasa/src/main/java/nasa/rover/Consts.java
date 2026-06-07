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

    // Validation error messages
    public static final String errorInvalidCommand   = "Syntax error: unknown command '%s' at position %d.";
    public static final String errorInvalidParam     = "Parameter error: value '%s' is out of range (min: %d, max: %d) at position %d.";
    public static final String errorMissingCommand   = "Syntax error: number '%s' at position %d is not followed by a valid command.";
    public static final String errorEmptyInput       = "Syntax error: empty input. Please enter at least one command.";

    // Cardinal direction names (index matches direction int: 0=N, 1=E, 2=S, 3=W)
    public static final String[] directionNames      = {"North (N)", "East (E)", "South (S)", "West (W)"};

    // Initial direction of the rover (1 = East)
    public static final int initialDirection         = 1;

    // Final result message
    public static final String resultSummary         = "Rover stopped at position (%d, %d) facing %s.";
}
